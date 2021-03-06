package com.geogreenapps.apps.indukaan.appconfig;

import com.geogreenapps.apps.indukaan.classes.Category;

import java.util.List;

public class Constances {


    public static int DISTANCE_CONST = 1024;
    //Change the url depending on the name of your web hosting
    public static String BASE_URL = AppConfig.BASE_URL;
    public static String BASE_URL_API = AppConfig.BASE_URL;
    public static String TERMS_OF_USE_URL = AppConfig.BASE_URL;
    public static String PRIVACY_POLICY_URL = AppConfig.BASE_URL;


    //Your AdMob Banner Unit ID
    //public static final String BANNER_UNIT_ID = Config.BANNER_UNIT_ID;
    //public static final String ADS_UNIT_ID = Config.ADS_UNIT_ID;
    //Your maps api key
    //public static final String MAPS_API_ID = Config.MAPS_API_ID;
    //You Can download other fonts , place it in the app and change the variables

    public static class ORDER_STATUS {
        public static final int ALL = -1;
        public static final int PENDING = 1;
        public static final int CONFIRMED = 2;
        public static final int PREPARING = 3;
        public static final int ON_DELIVERY = 4;
        public static final int DELIVERED = 5;
        public static final int CANCELLED = 6;
        public static final int REPORTED = 7;
    }



    public static class DELIVERY_STATUS {
        public static final int ALL = -1;
        public static final int PENDING = 0;
        public static final int ONGOING = 1;
        public static final int PICKED_UP = 2;
        public static final int DELIVERED = 3;
        public static final int REPORTED = 4;
    }


    public static class ModulesConfig {
        public final static String ORDERS_MODULE = "nsorder";
        public final static String ORDER_PAYMENT_MODULE = "order_payment";
        public final static String STORE_MODULE = "store";
        public final static String PRODUCT_MODULE = "product";
        public final static String OFFER_MODULE = "offer";
        public final static String MESSENGER_MODULE = "messenger";
        public final static String SLIDER_MODULE = "nsbanner";
        public final static String DELIVERY_MODULE = "delivery";
    }

    public static class OrderByFilter {
        public static final String RECENT = "recent";
        public static final String NEARBY = "nearby";
        public static final String TOP_RATED = "top_rated";
        public static final String NEARBY_TOP_RATED = "nearby_top_rated";
    }

    //WARNING :  DO NOT EDIT THIS
    public static class API {

        private static final String API_VERSION = "1.0";
        //store API's
        public static String API_USER_GET_STORES = BASE_URL_API + "/" + API_VERSION + "/store/getStores";
        public static String API_USER_GET_REVIEWS = BASE_URL_API + "/" + API_VERSION + "/store/getComments";
        public static String API_USER_UPDATE_STORE = BASE_URL_API + "/" + API_VERSION + "/webservice/updateStore";
        public static String API_RATING_STORE = BASE_URL_API + "/" + API_VERSION + "/store/rate";
        public static String API_SAVE_STORE = BASE_URL_API + "/" + API_VERSION + "/store/saveStore";
        public static String API_REMOVE_STORE = BASE_URL_API + "/" + API_VERSION + "/store/removeStore";
        //category API's
        public static String API_USER_GET_CATEGORY = BASE_URL_API + "/" + API_VERSION + "/category/getCategories";
        //uploader API's
        public static String API_USER_UPLOAD64 = BASE_URL_API + "/" + API_VERSION + "/uploader/uploadImage64";
        //user API's
        public static String API_USER_LOGIN = BASE_URL_API + "/" + API_VERSION + "/user/signIn";
        public static String API_USER_SIGNUP = BASE_URL_API + "/" + API_VERSION + "/user/signUp";
        public static String API_USER_CHECK_CONNECTION = BASE_URL_API + "/" + API_VERSION + "/user/checkUserConnection";
        public static String API_BLOCK_USER = BASE_URL_API + "/" + API_VERSION + "/user/blockUser";
        public static String API_GET_USERS = BASE_URL_API + "/" + API_VERSION + "/user/getUsers";
        public static String API_UPDATE_ACCOUNT = BASE_URL_API + "/" + API_VERSION + "/user/updateAccount";
        public static String API_USER_REGISTER_TOKEN = BASE_URL_API + "/" + API_VERSION + "/user/registerToken";
        public static String API_REFRESH_POSITION = BASE_URL_API + "/" + API_VERSION + "/user/refreshPosition";
        public static String API_PHONE_VERIFICATION = BASE_URL_API + "/" + API_VERSION + "/user/updatePhone";

        //setting API's
        public static String API_APP_INIT = BASE_URL_API + "/" + API_VERSION + "/setting/app_initialization";
        //messenger API's
        public static String API_LOAD_MESSAGES = BASE_URL_API + "/" + API_VERSION + "/messenger/loadMessages";
        public static String API_LOAD_DISCUSSION = BASE_URL_API + "/" + API_VERSION + "/messenger/loadDiscussion";
        public static String API_INBOX_MARK_AS_SEEN = BASE_URL_API + "/" + API_VERSION + "/messenger/markMessagesAsSeen";
        public static String API_INBOX_MARK_AS_LOADED = BASE_URL_API + "/" + API_VERSION + "/messenger/markMessagesAsLoaded";
        public static String API_SEND_MESSAGE = BASE_URL_API + "/" + API_VERSION + "/messenger/sendMessage";


        //product API's
        public static String API_GET_PRODUCTS = BASE_URL_API + "/" + API_VERSION + "/product/getProducts";
        //product API's
        public static String API_GET_OFFERS = BASE_URL_API + "/" + API_VERSION + "/offer/getOffers";


        //campaign API's
        public static String API_MARK_VIEW = BASE_URL_API + "/" + API_VERSION + "/campaign/markView";
        public static String API_MARK_RECEIVE = BASE_URL_API + "/" + API_VERSION + "/campaign/markReceive";


        //gallery
        public static String API_GET_GALLERY = BASE_URL_API + "/" + API_VERSION + "/gallery/getGallery";


        //Notification API's
        public static String API_NOTIFICATIONS_GET = BASE_URL_API + "/" + API_VERSION + "/nshistoric/getNotifications";
        public static String API_NOTIFICATIONS_COUNT_GET = BASE_URL_API + "/" + API_VERSION + "/nshistoric/getCount";
        public static String API_NOTIFICATIONS_EDIT_STATUS = BASE_URL_API + "/" + API_VERSION + "/nshistoric/changeStatus";
        public static String API_NOTIFICATIONS_REMOVE = BASE_URL_API + "/" + API_VERSION + "/nshistoric/remove";
        public static String API_NOTIFICATIONS_AGREEMENT = BASE_URL_API + "/" + API_VERSION + "/campaign/notification_agreement";

        //Orders
        public static String API_ORDERS_GET = BASE_URL_API + "/" + API_VERSION + "/nsorder/getOrders";
        public static String API_ORDERS_CREATE = BASE_URL_API + "/" + API_VERSION + "/nsorder/createOrder";
        public static String API_UPDATE_ORDER = BASE_URL_API + "/" + API_VERSION + "/delivery/updateOrder";


        //Modules
        public static String API_AVAILABLE_MODULES = BASE_URL_API + "/" + API_VERSION + "/modules_manager/availableModules";

        //payment
        public static String API_PAYMENT_GATEWAY = BASE_URL_API + "/" + API_VERSION + "/order_payment/getPayments";
        public static String API_PAYMENT_LINK = BASE_URL_API + "/" + API_VERSION + "/order_payment/get_payment_link";
        public static String API_PAYMENT_LINK_CALL = BASE_URL + "/order_payment/link_call";
        public static String API_APP_CONFIG = BASE_URL_API + "/" + API_VERSION + "/setting/getAppConfig";

        //Bookmark API's
        public static String API_SAVE_STORE_BOOKMARK = BASE_URL_API + "/" + API_VERSION + "/store/saveStore";
        public static String API_REMOVE_STORE_BOOKMARK = BASE_URL_API + "/" + API_VERSION + "/store/removeStore";
        public static String API_GET_BOOKMARKS = BASE_URL_API + "/" + API_VERSION + "/bookmark/getBookmarks";


        //Slider
        public static String API_GET_SLIDERS = BASE_URL_API + "/" + API_VERSION + "/nsbanner/getBanners";


    }


    public static class initConfig {

        //WARNING :  DO NOT EDIT THIS
        public static List<Category> ListCats;
        public static int Numboftabs;

        public static class fonts {
        }

        //WARNING :  DO NOT EDIT THIS
        public static class Tabs {

            public static final int HOME = 0;
            public static final int BOOKMAKRS = -1;
            public static final int MOST_RATED = -2;
            public static final int MOST_RECENT = -3;
            public static final int GEO = -4;
            public static final int CHAT = -5;
            public static final int NEARBY_PRODUCTS = -6;
        }

        public static class AppInfos {

            // set the description
            public static String ABOUT_CONTENT = AppConfig.ABOUT_CONTENT;

            // Your email that you wish that users on your app will contact you.
            public static String ADDRESS_CONTACT = AppConfig.ADDRESS_CONTACT;

        }
    }


}
