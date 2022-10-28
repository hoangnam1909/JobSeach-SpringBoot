package com.nhn.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface StatService {

    List<Map<String, String>> statNumberOfJobWithJobCategory();

    List<Map<String, String>> statNumberOfJobWithPosition();

    List<Map<String, String>> statNumberOfJobWithJobType();

    Map<String, String> statApplyJob(Date fromDate, Date toDate);

    Map<String, String> statJobCreatedDate(Date fromDate, Date toDate);

}
