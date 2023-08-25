package org.example.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class AOPCntr {

    private static final Logger log = LoggerFactory.getLogger(AOPCntr.class);



//    @Pointcut("execution(* org.example.Controller.*.*(..))")
//    public void CntrMethodExecuting() {
//    }
//
//    @Around(value = "CntrMethodExecuting()")
//    public void beforeAdvice(ProceedingJoinPoint joinPoint) {
//        System.out.println("Открытие транзакции...");
//        try {
//            log.info("Начало выполнения метода - {}, класса- {},с входными данными - {}",
//                    joinPoint.getSignature().getName(),
//                    joinPoint.getSourceLocation().getWithinType().getName(),
//                    joinPoint.getArgs());
//            log.info("Успешно выполнен метод - {}, класса- {}\n",
//                    joinPoint.getSignature().getName(),
//                    joinPoint.getSourceLocation().getWithinType().getName());
//        }
//        catch (Throwable throwable) {
//            log.error("Метод - {}, класса- {}, был аварийно завершен с исключением - {}\n",
//                    joinPoint.getSignature().getName(),
//                    joinPoint.getSourceLocation().getWithinType().getName(),
//                    throwable);
//        }
//    }
}
