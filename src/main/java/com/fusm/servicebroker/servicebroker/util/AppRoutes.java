package com.fusm.servicebroker.servicebroker.util;

public class AppRoutes {
    public static final String PRIVATE_ROUTE = "${broker-routes.private}";
    public static final String PUBLIC_ROUTE = "${broker-routes.public}";

    public static final String CATALOG_ROUTE = "${ms-catalogs.catalogs.path}";
    public static final String CATALOG_ITEM_ROUTE = "${ms-catalogs.catalog-item.path}";
    public static final String CATALOG_TYPE_ROUTE = "${ms-catalogs.catalog-type.path}";

    public static final String PROGRAM_ROUTE = "${ms-program.programs.path}";
    public static final String TRACEABILITY_ROUTE = "${ms-program.trace.path}";

    public static final String WORKFLOW_ROUTE = "${ms-workflow.workflow.path}";

    public static final String LOGIN_ROUTE = "${ms-login.login.path}";

    public static final String MODULE_ROUTE = "${ms-safety-mesh.module.path}";
    public static final String PERMISSION_ROUTE = "${ms-safety-mesh.permission.path}";
    public static final String ROLE_ROUTE = "${ms-safety-mesh.role.path}";
    public static final String COLUMN_ROUTE = "${ms-safety-mesh.column.path}";

    public static final String SINU_ROUTE = "${ms-sinu.path}";

    public static final String EVENT_ROUTE = "${ms-event.event.path}";

    public static final String DOCUMENT_ROUTE = "${ms-document.document.path}";

    public static final String NEWS_ROUTE = "${ms-news.path}";

    public static final String SETTINGS_ROUTE = "${ms-settings.path}";

    public static final String EPORTAFOLIO_ROUTE = "${ms-eportafolio.path}";

    public static final String AUTHORIZER_ROUTE = "${ms-authorizer.authorizer.path}";

    public static final String TEMPLATE_ROUTE = "${ms-notification.template.path}";

}
