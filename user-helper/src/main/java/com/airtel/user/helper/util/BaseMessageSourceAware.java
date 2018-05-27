package com.airtel.user.helper.util;

/**
 * @author A1FB7X11
 *
 */

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import com.airtel.user.helper.dto.StatusBean;


public abstract class BaseMessageSourceAware implements MessageSourceAware {
	MessageSource messageSource;

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	protected String getMessage(String key, String defaultMessage) {
		return messageSource.getMessage(key, null, defaultMessage, null);
	}

	protected String getMessage(String key, String defaultMessage,
			Object... params) {
		return messageSource.getMessage(key, params, defaultMessage, null);
	}

	protected StatusBean getStatusBean(String code, String key,
			String defaultMessage) {
		return new StatusBean(code, messageSource.getMessage(key, null,
				defaultMessage, null));
	}

	protected StatusBean getStatusBean(String code, String key,
			String defaultMessage, Object... params) {
		return new StatusBean(code, messageSource.getMessage(key, params,
				defaultMessage, null));
	}

}
