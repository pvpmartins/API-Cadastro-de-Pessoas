//package com.example.Attornatus.teste.ErrorHandles;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//
//import javax.xml.validation.Validator;
//
//public class CustomRepositoryRestConfigurerAdapter {
//    @Configuration
//    public class CustomRepositoryRestConfigurerAdapter extends RepositoryRestConfigurerAdapter {
//
//
//        @Bean
//        public Validator validator() {
//            return new LocalValidatorFactoryBean();
//        }
//
//        @Override
//        public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
//            validatingListener.addValidator("afterCreate", validator());
//            validatingListener.addValidator("beforeCreate", validator());
//            validatingListener.addValidator("afterSave", validator());
//            validatingListener.addValidator("beforeSave", validator());
//        }
//    }
//}
