/**
 * Copyright (c) 2021 GregTech-6 Team
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

package gregapi.network;

import java.util.EnumMap;
import java.util.List;
import java.util.UUID;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import gregapi.util.UT;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.MessageToMessageCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
@Sharable
public final class NetworkHandler extends MessageToMessageCodec<FMLProxyPacket, IPacket> implements INetworkHandler {
	private final EnumMap<Side, FMLEmbeddedChannel> mChannel;
	private final IPacket[] mPacketTypes;
	private final String mModID;
	
	/**
	 * Just instantiate your Network Handler once with this simple Constructor and everything else should be done.
	 * 
	 * For usage keep that instance in a Variable somewhere so you can send Packets.
	 * 
	 * For an example look into the Main File (GT_API), where I initialise the API Network Handler.
	 * 
	 * @param aModID the ID of your Mod.
	 * @param aChannelName Name of your Channel (use 4 Characters or less, we don't want to Lag out the Connection), the GT Channel is called "GREG" and the API Channel is called "GAPI".
	 * @param aPacketTypes An Array of your Packet Types (an empty instance of every Packet you want to use for decoding). Remember that "getPacketID" must return a for your Handler individual Number. All 256 Byte Values are possible. Yes I mean the negative ones.
	 */
	public NetworkHandler(String aModID, String aChannelName, IPacket... aPacketTypes) {
		mModID = aModID;
		if (aChannelName.length() > 4) throw new IllegalArgumentException("String for Channel Name must contain 4 Characters or less!");
		mChannel = NetworkRegistry.INSTANCE.newChannel(aChannelName, this, FMLCommonHandler.instance().getSide()==Side.CLIENT?new HandlerClient(this):new HandlerServer(this));
		mPacketTypes = new IPacket[256];
		for (int i = 0; i < aPacketTypes.length; i++) {
			int tID = UT.Code.unsignB(aPacketTypes[i].getPacketID());
			if (mPacketTypes[tID] == null) mPacketTypes[tID] = aPacketTypes[i]; else throw new IllegalArgumentException("Duplicate Packet ID! " + tID);
		}
	}
	
	@Override
	protected void encode(ChannelHandlerContext aContext, IPacket aPacket, List<Object> aOutput) throws Exception {
		aOutput.add(new FMLProxyPacket(Unpooled.buffer().writeByte(aPacket.getPacketID()).writeBytes(aPacket.encode().toByteArray()), aContext.channel().attr(NetworkRegistry.FML_CHANNEL).get()));
	}
	
	@Override
	protected void decode(ChannelHandlerContext aContext, FMLProxyPacket aPacket, List<Object> aOutput) throws Exception {
		ByteArrayDataInput aData = ByteStreams.newDataInput(aPacket.payload().array());
		int aID = UT.Code.unsignB(aData.readByte());
		if (mPacketTypes[aID] == null) {
			FMLLog.warning("Your Version of '" + mModID + "' definetly does not match the Version installed on the Server you joined! Do not report this as a Bug! You failed to install/update the proper Version of '" + mModID + "' all by yourself!");
		} else {
			aOutput.add(mPacketTypes[aID].decode(aData));
		}
	}
	
	@Override
	public void sendToServer(IPacket aPacket) {
		if (aPacket == null) return;
		FMLEmbeddedChannel tChannel = getChannel(Side.CLIENT);
		tChannel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
		tChannel.writeAndFlush(aPacket);
	}
	
	@Override
	public void sendToPlayer(IPacket aPacket, EntityPlayerMP aPlayer) {
		if (aPacket == null) return;
		FMLEmbeddedChannel tChannel = getChannel(Side.SERVER);
		tChannel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
		tChannel.attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(aPlayer);
		tChannel.writeAndFlush(aPacket);
	}
	
	@Override
	public void sendToAllAround(IPacket aPacket, TargetPoint aPosition) {
		if (aPacket == null) return;
		FMLEmbeddedChannel tChannel = getChannel(Side.SERVER);
		tChannel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
		tChannel.attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(aPosition);
		tChannel.writeAndFlush(aPacket);
	}
	
	@Override public void sendToAllPlayersInRange(IPacket aPacket, World aWorld, ChunkCoordinates aCoords) {sendToAllPlayersInRange(aPacket, aWorld, aCoords.posX, aCoords.posZ);}
	@Override public void sendToAllPlayersInRange(IPacket aPacket, World aWorld, int aX, int aZ) {
		if (aPacket == null) return;
		if (aWorld != null && !aWorld.isRemote) for (Object tObject : aWorld.playerEntities) {
			if (tObject instanceof EntityPlayerMP) {
				EntityPlayerMP tPlayer = (EntityPlayerMP)tObject;
				Chunk tChunk = aWorld.getChunkFromBlockCoords(aX, aZ);
				if (tPlayer.getServerForPlayer().getPlayerManager().isPlayerWatchingChunk(tPlayer, tChunk.xPosition, tChunk.zPosition)) sendToPlayer(aPacket, tPlayer);
			} else return;
		}
	}
	
	@Override public void sendToPlayerIfInRange(IPacket aPacket, UUID aPlayer, World aWorld, ChunkCoordinates aCoords) {sendToPlayerIfInRange(aPacket, aPlayer, aWorld, aCoords.posX, aCoords.posZ);}
	@Override public void sendToPlayerIfInRange(IPacket aPacket, UUID aPlayer, World aWorld, int aX, int aZ) {
		if (aPacket == null) return;
		if (aWorld != null && !aWorld.isRemote) for (Object tObject : aWorld.playerEntities) {
			if (tObject instanceof EntityPlayerMP) {
				EntityPlayerMP tPlayer = (EntityPlayerMP)tObject;
				if (tPlayer.getUniqueID().equals(aPlayer)) {
					Chunk tChunk = aWorld.getChunkFromBlockCoords(aX, aZ);
					if (tPlayer.getServerForPlayer().getPlayerManager().isPlayerWatchingChunk(tPlayer, tChunk.xPosition, tChunk.zPosition)) {
						sendToPlayer(aPacket, tPlayer);
					}
					return;
				}
			} else return;
		}
	}
	
	@Override public void sendToAllPlayersInRangeExcept(IPacket aPacket, UUID aPlayer, World aWorld, ChunkCoordinates aCoords) {sendToAllPlayersInRangeExcept(aPacket, aPlayer, aWorld, aCoords.posX, aCoords.posZ);}
	@Override public void sendToAllPlayersInRangeExcept(IPacket aPacket, UUID aPlayer, World aWorld, int aX, int aZ) {
		if (aPacket == null) return;
		if (aWorld != null && !aWorld.isRemote) for (Object tObject : aWorld.playerEntities) {
			if (tObject instanceof EntityPlayerMP) {
				EntityPlayerMP tPlayer = (EntityPlayerMP)tObject;
				if (!tPlayer.getUniqueID().equals(aPlayer)) {
					Chunk tChunk = aWorld.getChunkFromBlockCoords(aX, aZ);
					if (tPlayer.getServerForPlayer().getPlayerManager().isPlayerWatchingChunk(tPlayer, tChunk.xPosition, tChunk.zPosition)) {
						sendToPlayer(aPacket, tPlayer);
					}
				}
			} else return;
		}
	}
	
	@Override
	public FMLEmbeddedChannel getChannel(Side aSide) {
		return mChannel.get(aSide);
	}
	
	@Sharable
	static final class HandlerClient extends SimpleChannelInboundHandler<IPacket> {
		public final INetworkHandler mNetworkHandler;
		
		public HandlerClient(INetworkHandler aNetworkHandler) {
			mNetworkHandler = aNetworkHandler;
		}
		
		@Override
		protected void channelRead0(ChannelHandlerContext ctx, IPacket aPacket) throws Exception {
			aPacket.process(Minecraft.getMinecraft().thePlayer == null ? null : Minecraft.getMinecraft().thePlayer.worldObj, mNetworkHandler);
//          DEB.println(aPacket.getClass().getName());
//          if (aPacket instanceof PacketCoordinates) DEB.println(" X: " + ((PacketCoordinates)aPacket).mX + " - Y: " + ((PacketCoordinates)aPacket).mY + " - Z: " + ((PacketCoordinates)aPacket).mZ);
		}
	}
	
	@Sharable
	static final class HandlerServer extends SimpleChannelInboundHandler<IPacket> {
		public final INetworkHandler mNetworkHandler;
		
		public HandlerServer(INetworkHandler aNetworkHandler) {
			mNetworkHandler = aNetworkHandler;
		}
		
		@Override
		protected void channelRead0(ChannelHandlerContext ctx, IPacket aPacket) throws Exception {
			aPacket.process(null, mNetworkHandler);
		}
	}
}
