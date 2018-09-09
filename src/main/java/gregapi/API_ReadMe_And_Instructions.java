package gregapi;

/**
 * @author Gregorius Techneticies
 * 
 * This Text is a little bit old, but I think most of it still fits.
 * 
 * ----------------------------------------------------------------
 * There are hooks to load after the 2 API Mods and GT itself.
 * They are in the form of Lists containing "Runnable" classes.
 * Those Lists are to be found in "gregapi.api.Abstract_Mod".
 * The Mod Objects themselves are in "gregapi.data.CS".
 * I have put them at the top of the Class so it's unoverseeable.
 * ----------------------------------------------------------------
 * If you want your Mod dependent on this API, then instead of
 * adding a dependency to GregTech add a dependency to this API
 * 
 * In order to do so just insert the following at your Loading
 * Order: "required-after:gregapi; required-before:gregapi_post"
 * 
 * This way you can access all the Features I have without needing
 * the Technology, Items, Blocks, Recipes or anything else of my
 * Mod to be there.
 * 
 * Instead you just need either the Core-API or GregTech itself to
 * be installed, so that your Mod works. Of course the Core-API
 * might be more desirable to some of your Users since it doesn't
 * do anything, unlike GregTech, which changes almost everything.
 * ================================================================
 * Now for some other Informations
 * ================================================================
 * if you have to choose an unlocalised Name or similar, then NEVER
 * use a Name which starts with "gt."! This is MY Name Prefix and
 * not yours. This happens way too often especially with people
 * decompiling my Code to find out how they have to use Stuff.
 * ----------------------------------------------------------------
 * You may have noticed, that I try to avoid the usage of the Data
 * Types "Double" and "Float". This is because they fail after they
 * reach a few Digits too much. They cannot even display Integers
 * properly, resulting in imprecise Values and worse.
 * 
 * That is why I made the Parameters as Integer or Long for most
 * of the Functionality.
 * 
 * Trust me, there were already enough Problems due to Floats and
 * Doubles. They may look nice and are easy to use, but they are
 * the Devil. And I mean the Canadian Devil, not the regular one.
 * ----------------------------------------------------------------
 * Most of the Constants are found inside the data package.
 * ----------------------------------------------------------------
 * To use the NetworkHandler, just create an instance of it and
 * insert a Dummy for every single Packet you want to send/receive.
 * The implementation of IPacket is completely left to you.
 * 
 * If you need more than 256 different IPackets, consider making a
 * second NetworkHandler with a different Channel-ID.
 * 
 * The API does offer a few Packets for certain Sync stuff.
 * ----------------------------------------------------------------
 */