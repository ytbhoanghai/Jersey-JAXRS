package com.nguyen;

import com.nguyen.helper.JWTHelper;
import com.nguyen.repository.UserRepository;
import org.glassfish.hk2.utilities.binding.AbstractBinder;


public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {

        bind(UserRepository.class).to(UserRepository.class);
    }
}
