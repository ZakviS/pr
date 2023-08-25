package org.example.AOP;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AOPServices {

    private static final Logger log = LoggerFactory.getLogger(AOPServices.class);



    @Pointcut("execution(* org.example.Controller.*.*(..))")
    public void CntrMethodExecuting() {
    }

    @Pointcut("execution(* org.example.services.*.*(..))")
    public void ServicesMethodExecuting() {
    }

    @Before("ServicesMethodExecuting()" )
    public void beforeExecution(JoinPoint joinPoint){
        log.info("Начало выполнения метода - {}, класса- {},с входными данными - {}",
                joinPoint.getSignature().getName(),
                joinPoint.getSourceLocation().getWithinType().getName(),
                joinPoint.getArgs());
    }

    @AfterReturning(value = "ServicesMethodExecuting()", returning = "returningValue")
    public void recordSuccessfulExecution(JoinPoint joinPoint, Object returningValue) {
        if (returningValue != null) {

            log.info("Успешно выполнен метод - {}, класса- {}, с результатом выполнения - {}\n",
                    joinPoint.getSignature().getName(),
                    joinPoint.getSourceLocation().getWithinType().getName(),
                    returningValue);
        }
        else {
            log.info("Успешно выполнен метод - {}, класса- {}\n",
                    joinPoint.getSignature().getName(),
                    joinPoint.getSourceLocation().getWithinType().getName());
        }
    }

    @AfterThrowing(value = "ServicesMethodExecuting()", throwing = "exception")
    public void recordFailedExecution(JoinPoint joinPoint, Exception exception) {

        log.error("Метод - {}, класса- {}, был аварийно завершен с исключением - {}\n",
                joinPoint.getSignature().getName(),
                joinPoint.getSourceLocation().getWithinType().getName(),
                exception);
    }

    @Before("CntrMethodExecuting()" )
    public void CntrBeforeExecution(JoinPoint joinPoint){
        log.info("Начало выполнения метода - {}, класса- {},с входными данными - {}",
                joinPoint.getSignature().getName(),
                joinPoint.getSourceLocation().getWithinType().getName(),
                joinPoint.getArgs());
    }

    @AfterReturning(value = "CntrMethodExecuting()", returning = "returningValue")
    public void CntrRecordSuccessfulExecution(JoinPoint joinPoint, Object returningValue) {
        if (returningValue != null) {

            log.info("Успешно выполнен метод - {}, класса- {}, с результатом выполнения - {}\n",
                    joinPoint.getSignature().getName(),
                    joinPoint.getSourceLocation().getWithinType().getName(),
                    returningValue);
        }
        else {
            log.info("Успешно выполнен метод - {}, класса- {}\n",
                    joinPoint.getSignature().getName(),
                    joinPoint.getSourceLocation().getWithinType().getName());
        }
    }

    @AfterThrowing(value = "CntrMethodExecuting()", throwing = "exception")
    public void CntrRecordFailedExecution(JoinPoint joinPoint, Exception exception) {

        log.error("Метод - {}, класса- {}, был аварийно завершен с исключением - {}\n",
                joinPoint.getSignature().getName(),
                joinPoint.getSourceLocation().getWithinType().getName(),
                exception);
    }



}
