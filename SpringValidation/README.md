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
