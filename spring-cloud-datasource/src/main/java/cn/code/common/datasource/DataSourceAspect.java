package cn.code.common.datasource;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Aspect
@Order(-1)
@Component
public class DataSourceAspect {

    @Autowired
    private DruidUtil druidUtil;

    //切点，指定相应的方法进行切入，加有DataSource的注解进行调用
    @Before("@annotation(DataSource)")
    public void beforeServiceMethodExecute(JoinPoint point) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method targetMethod = methodSignature.getMethod();
        Parameter[] parameters = targetMethod.getParameters();
        Object[] args = point.getArgs();
        if (null == parameters || parameters.length == 0) {
            return;
        }
        for (int i = 0; i < parameters.length; i++) {
            Object arg = args[i];
            if (arg instanceof DataSourceModel) {
                DataSourceModel dataSourcemodel = (DataSourceModel) args[i];
                if (modelFlag(dataSourcemodel)) {
                    druidUtil.switchDB(dataSourcemodel);
                    break;
                }
            }
        }
    }

    /**
     * 判断调用方法
     *
     * @param model
     * @return
     */
    public boolean modelFlag(DataSourceModel model) {
        return model != null && StringUtils.isNotBlank(model.getDriverClassName())
            && StringUtils.isNotBlank(model.getUrl()) && StringUtils.isNotBlank(model.getUsername());
    }

    /**
     * 切面完成之后，移除数据源
     *
     * @param point
     * @return
     * @author luojiayu
     * @date 2021/1/25
     */
    @After("@annotation(DataSource))")
    public void afterServiceMethodExecute(JoinPoint point) {
        DynamicDataSourceHandler.clear();
    }
}

