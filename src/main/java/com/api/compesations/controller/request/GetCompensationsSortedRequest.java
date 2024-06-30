package com.api.compesations.controller.request;

public record GetCompensationsSortedRequest (
    String sortBy,
    String order,
    Integer pageNo,
    Integer pageSize
) {}
