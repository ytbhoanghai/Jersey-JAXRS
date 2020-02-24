package com.nguyen;

import com.nguyen.repository.StudentRepository;
import com.nguyen.repository.StudentRepositoryService;
import lombok.NoArgsConstructor;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

@NoArgsConstructor
public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(StudentRepository.class).to(StudentRepositoryService.class);
    }
}
