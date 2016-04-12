package com.infinum.utils.json.response

import groovy.transform.CompileStatic

/*
 * Defined using params used in Grails List Queries:  grails-docs-2.1/ref/Domain Classes/list.html
 */
@CompileStatic
class PagingAndSortingOptions {

    static final String ORDER_DESC = 'desc'
    static final String ORDER_ASC = 'asc'
    
    /**
     * The maximum number to list
     */
    Integer max
    
    /**
     * The offset from the first result to list from
     */
    Integer offset
    
    /**
     * How to order the list, either "desc" or "asc".  Default is ascending
     */
    String order
    
    /**
     * The property name to sort by
     */
    String sort
    
    /**
     * Whether to ignore the case when sorting. Default is true.
     */
    Boolean ignoreCase

}