package com.example.web.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.web.service.UserAnswerService;

@Component
public class UserAnswerJob {
    @Autowired()
    private UserAnswerService UserAnswerService;

    /**
     * 每隔5s检测试卷是否停止了
     */
    @Scheduled(cron = "*/5 * * * * ?")
    private void AutoSubmit() {
        UserAnswerService.AutoSubmit();
    }

}
