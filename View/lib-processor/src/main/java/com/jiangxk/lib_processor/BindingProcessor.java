package com.jiangxk.lib_processor;


import com.jiangxk.lib_annotations.BindView;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

public class BindingProcessor extends AbstractProcessor {
    Filer filer;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
//        String packageName = "com.jiangxk.annotationprocessing";
//        ClassName className = ClassName.get(packageName, "MainActivityBinding");
//        TypeSpec builtClass = TypeSpec.classBuilder(className)
//                .addModifiers(Modifier.PUBLIC)
//                .addMethod(MethodSpec.constructorBuilder()
//                        .addModifiers(Modifier.PUBLIC)
//                        .addParameter(ClassName.get(packageName, "MainActivity"), "activity")
//                        .addStatement("activity.textView = activity.findViewById(R.id.textView)")
//                        .build())
//                .build();
//        try {
//            JavaFile.builder(packageName, builtClass)
//                    .build()
//                    .writeTo(filer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        for (Element rootElement : roundEnvironment.getRootElements()) {
            String packageStr = rootElement.getEnclosingElement().toString();
            String classStr = rootElement.getSimpleName().toString();
            ClassName className = ClassName.get(packageStr, classStr + "Binding");
            MethodSpec.Builder constructorBuilder = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(ClassName.get(packageStr, classStr), "activity");
            boolean hasBinding = false;

            for (Element enclosedElement : rootElement.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.FIELD) {
                    BindView bindView = enclosedElement.getAnnotation(BindView.class);
                    if (bindView != null) {
                        hasBinding = true;

                        constructorBuilder.addStatement("activity.$N = activity.findViewById($L)",
                                enclosedElement.getSimpleName(), bindView.value());
                    }
                }
            }

            TypeSpec builtClass = TypeSpec.classBuilder(className)
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(constructorBuilder.build())
                    .build();

            if (hasBinding) {
                try {
                    JavaFile.builder(packageStr, builtClass)
                            .build()
                            .writeTo(filer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(BindView.class.getCanonicalName());
    }
}