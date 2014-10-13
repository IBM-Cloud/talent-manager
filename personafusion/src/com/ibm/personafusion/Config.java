package com.ibm.personafusion;

import com.ibm.personafusion.db.CloudantClient;

public class Config 
{
    
    /** Cloudant **/
    public static final int CLOUDANT_PORT = 443;
    public static final String CLOUDANT_NAME = "talent-manager";
    
    /** Watson User Modeling **/
    
    public static final String WATSON_PROF_API = "/api/v2/profile";
    public static final String WATSON_VIZ_API = "/api/v2/visualize";
    
    public static CloudantClient cc = new CloudantClient();

    /* Mobile Data Config */
    public static final String MOBILE_DATA_APP_ID = "8cefcce7-3134-4612-a953-14053685c9bd";
    public static final String MOBILE_DATA_APP_SECRET = "86f3191cfcb7d386e69774fbb661aaa9951c11a5";
}
