package com.example.kotlin25.config.aop


import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch

@Aspect
    @Component
    class StopWatchAspect {

        private val logger = org.slf4j.LoggerFactory.getLogger("Execution Time Logger")


        @Around("@annotation(com.example.kotlin25.config.aop.StopWatch)")
        fun run(joinPoint: ProceedingJoinPoint) {
            val stopWatch = StopWatch()

            stopWatch.start()
            joinPoint.proceed()
            stopWatch.stop()

            val timeElapsedMs = stopWatch.totalTimeMillis

            val methodName = joinPoint.signature.name
            val methodArguments = joinPoint.args

            logger.info("Method Name: $methodName | Arguments: ${methodArguments.joinToString(", ")} | Execution Time: ${timeElapsedMs}ms")
        }
    }
