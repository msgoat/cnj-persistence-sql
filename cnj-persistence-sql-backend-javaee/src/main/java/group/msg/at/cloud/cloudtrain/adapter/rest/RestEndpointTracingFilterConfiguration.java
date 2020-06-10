package group.msg.at.cloud.cloudtrain.adapter.rest;

import group.msg.at.cloud.common.web.filter.RestEndpointTracingFilter;

import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "RestEndpointTracingFilter", urlPatterns = { "/api/v1/tasks/*" })
public class RestEndpointTracingFilterConfiguration extends RestEndpointTracingFilter {
}
