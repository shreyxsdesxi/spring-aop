# Spring AOP

Aspect Oriented Programming (AOP) is a programming paradigm aiming to extract cross-cutting functionalities, such as logging, into what’s known as “Aspects”.

This is achieved by adding behavior (“Advice”) to existing code without changing the code itself. We specify which code we want to add the behavior to using special expressions (“Pointcuts”).

## AOP Basic Terminologies

The terminologies we will discuss are not Spring specific, they are general AOP concepts that Spring implements.

Let’s start by introducing the four main building blocks of any AOP example in Spring.

### JoinPoint
Simply put, a JoinPoint is a point in the execution flow of a method where an Aspect (new behavior) can be plugged in.

### Advice
It’s the behavior that addresses system-wide concerns (logging, security checks, etc…). This behavior is represented by a method to be executed at a JoinPoint. This behavior can be executed Before, After, or Around the JoinPoint according to the Advice type as we will see later.

### Pointcut
A Pointcut is an expression that defines at what JoinPoints a given Advice should be applied.

- **execution**

    This is used to match a joinPoint method’s signature.
    
````java
@Component  
@Aspect  
public class LoggingAspect {  

@Pointcut("execution(public void io.reflectoring.springboot.aop.ShipmentService.shipStuffWithBill())")  
public void logPointcutWithExecution(){}  
}
````

````java
@Component  
@Aspect  
public class LoggingAspect {  
      
    @Pointcut("execution(public void io.reflectoring.springboot.aop.ShipmentService.shipStuffWithBill())")  
    public void logPointcutWithExecution(){}  
  
    @Before("logPointcutWithExecution()")  
    public void logMethodCallsWithExecutionAdvice() {  
        System.out.println("In Aspect from execution");  
  }  
}
````

we can also use Wildcards to write a more flexible expression. For example, the expression

``execution(public void io.reflectoring.springboot.aop.ShipmentService.*())``

``execution(public void io.reflectoring.springboot.aop.ShipmentService.*(..))``

will match any public void method that takes zero or more parameters in ShipmentService.

- within

  This is used to match all the JoinPoint methods in a given class, package, or sub-package.
  
  ```java
  @Component  
  @Aspect  
  public class LoggingAspect {
  @Pointcut("within(io.reflectoring.springboot.aop.BillingService)")  
  public void logPointcutWithin() {}
  
      @Before("logPointcutWithin()")  
      public void logMethodCallsWithinAdvice() {  
          System.out.println("In Aspect from within");  
      }
  }
  ```
  
  - @annotation

    This is used to match a JointPoint method annotated with a given annotation. We used it in our first example of AOP.

    ```java
    @Component  
    @Aspect  
    public class LoggingAspect {  
    
    @Pointcut("@annotation(Log)")  
    public void logPointcut(){  
    }  
    @Before("logPointcut()")  
    public void logAllMethodCallsAdvice(){  
    System.out.println("In Aspect");  
    }
    }
    ```

## Combining PointCut Expressions

We can combine more than a single PointCut expression using logical operators, which are && (and), || (or) and ! (not) operators.

### Aspect
Aspect is a class in which we define Pointcuts and Advices.