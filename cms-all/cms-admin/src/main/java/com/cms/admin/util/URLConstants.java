package com.cms.admin.util;

public class URLConstants {

    public final static String AUTHORIZATION_URL = "auth";
   
    public final static String GET_ALL_USER_DETAILS = "user/all-users";
    public final static String GET_USER_BY_USERID = "user/byUserId/%d";
    public final static String UPDATE_USER_BY_USERID = "user/save";
    
    public final static String GET_ALL_HOST_DETAILS = "host/all-hosts";
    public final static String GET_HOST_BY_HOSTID = "host/byHostId/%d";
    public final static String GET_HOST_BY_HOSTNAME = "host/byHostName/%s";
    public final static String SAVE_HOST_BY_HOSTID = "host/insert";
    public final static String UPDATE_HOST_BY_HOSTID = "host/update";
    
    public final static String GET_ALL_TEMPLET_DETAILS = "templet/all-templets";
    public final static String GET_TEMPLET_BY_TEMPLETID = "templet/byTempletId/%d";
    public final static String GET_TEMPLET_BY_TEMPLETNAME = "templet/byTempletName/%s/hostId/%d";
    public final static String SAVE_TEMPLET_BY_TEMPLETID = "templet/insert";
    public final static String UPDATE_TEMPLET_BY_TEMPLETID = "templet/update";
    public final static String GET_TEMPLET_BY_HOSTID = "host/getTempletByHostId/%d";
    public final static String TEMPLET_COUNT = "page/getTempletCount/%d";
    
    
    public final static String GET_ALL_CONTAINER_DETAILS = "container/all-containers";
    public final static String GET_CONTAINER_BY_CONTAINERID = "container/byContainerId/%d";
    public final static String SAVE_CONTAINER_BY_CONTAINERID = "container/insert";
    public final static String UPDATE_CONTAINER_BY_CONTAINERID = "container/update";
 
    
    public final static String GET_ALL_PAGE_DETAILS = "page/all-pages";
    public final static String GET_PAGE_BY_PAGE_ID = "page/byPageId/%d";
    public final static String GET_PAGE_BY_PAGE_NAME = "page/byPageName/%s/hostId/%d";
    public final static String SAVE_PAGE_BY_PAGE_ID = "page/insert";
    public final static String GET_PAGE_BY_HOST_ID = "page/getPageByHostId/%d";
    public final static String UPDATE_PAGE_BY_PAGE_ID = "page/update";
    public final static String PAGE_COUNT = "page/getPageCount/%d";
    
    
    public final static String GET_ALL_HEADER_DETAILS = "header/all-headers";
    public final static String GET_HEADER_BY_HEADER_ID = "header/byFooterId/%d";
    public final static String GET_HEADER_BY_HEADER_NAME = "header/byFooterName/%s/hostId/%d";
    public final static String SAVE_HEADER_BY_HEADER_ID = "header/insert";
    public final static String GET_HEADER_BY_HOST_ID = "templet/getFooterByHostId/%d";
    public final static String UPDATE_HEADER_BY_HEADER_ID = "header/update";
    public final static String HEADER_COUNT = "header/getFooterCount/%d";
    
    public final static String GET_ALL_FOOTER_DETAILS = "footer/all-footers";
    public final static String GET_FOOTER_BY_FOOTER_ID = "footer/byFooterId/%d";
    public final static String GET_FOOTER_BY_FOOTER_NAME = "footer/byFooterName/%s/hostId/%d";
    public final static String SAVE_FOOTER_BY_FOOTER_ID = "footer/insert";
    public final static String GET_FOOTER_BY_HOST_ID = "templet/getFooterByHostId/%d";
    public final static String UPDATE_FOOTER_BY_FOOTER_ID = "footer/update";
    public final static String FOOTER_COUNT = "footer/getFooterCount/%d";
    
    
    
    
    public static final String GET_LANDINGPAGE_URL = "OM/%s/%s/%s";
    
    public final static String GET_ALL_EMPLOYEE = "";
    

    public final static String GET_ALL_MENU_BY_HOST_ID = "menu/hostId/%d";
    public final static String GET_ALL_MENU_BY_HOST_NAME = "menu/hostId/%s";
    public final static String GET_ALL_MENU_BY_PAGE_ID = "menu/pageId/%d";
    public final static String GET_ALL_MENU_BY_PAGE_NAME = "menu/pageName/%s";
    public final static String GET_ALL_MENU_BY_PARENTS_ID = "menu/parentsId/%d";
    public final static String GET_ALL_MENU_BY_CHILD_ID = "menu/childId/%d";
    public final static String GET_ALL_MENU_BY_PARENTS_ID_AND_CHILD_ID = "menu-by/%d/%d";
    public final static String SAVE_MENU="menu/";
    public final static String MENULIST="menu/menu/hostName/pageName/%s/%s";
    public final static String MENU_LIST_BY_CHILDPAGE="menu/menu/hostName/childPageName/%s/%s";
    
    public final static String UPLOAD_DOCUMENT="document-upload/document"; 
}
