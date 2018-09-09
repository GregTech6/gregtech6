package gregapi;

import static gregapi.data.CS.*;


/**
 * @author Gregorius Techneticies
 */
public class GT_API_Proxy_Server extends GT_API_Proxy {
	public GT_API_Proxy_Server() {
		super();
		CODE_SERVER = T;
		CODE_CLIENT = F;
		CODE_UNCHECKED = F;
	}
}