# Spring Validation Demo
## RequestBody
1. 在对象中增加validation注解
2. 在入参RequestBody对象前加注解@Validated
3. 增加全局异常处理 捕获MethodArgumentNotValidException并处理
4. 若2中对象属性包含对象或者List 需要在字段上增加注解@Valid 进行嵌套的处理

## PathVariable or RequestParam
1. 在类上增加注解@Validated
2. 在入参前增加validation注解
3. 增加全局异常处理 捕获ConstraintViolationException并处理

## 分组校验
validation注解中指定group 在@Validated中也指定group的class

## 自定义校验注解
1. 自定义注解MyValidation
2. 自定义注解处理MyValidationHandler 并实现ConstraintValidator<A, T>接口  
A表示自定义注解MyValidation T表示要处理的字段类型
3. 重写isValid方法 自定义处理逻辑

## 编程式校验
1. 注入private javax.validation.Validator globalValidator;
2. 校验 globalValidator.validate(${bean}, ${bean.group.class}).var;
3. 判断返回值是否为空

## 快速失败 fail fast
默认校验所有参数 若开启fail fast 有参数校验失败就返回
```
Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();
validator.validate(${bean}, ${bean.group.class});
``` 
