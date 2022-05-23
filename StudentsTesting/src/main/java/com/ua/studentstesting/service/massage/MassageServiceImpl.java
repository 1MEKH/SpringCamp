package com.ua.studentstesting.service.massage;

import com.ua.studentstesting.configs.AppSettings;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class MassageServiceImpl implements MassageService {

    final private MessageSource messageSource;
    final private AppSettings appSettings;

    public MassageServiceImpl(MessageSource messageSource, AppSettings appSettings) {
        this.messageSource = messageSource;
        this.appSettings = appSettings;
    }

    @Override
    public String getMassage(String message) {
        return messageSource.getMessage(message, null, appSettings.getLocale());
    }

}
