## 启用 DataBinding

在AndroidStudio中使用DataBinding很简单，只需在gradle文件中声明启用DataBinding

```gradle
android {

    //...

    dataBinding{
        enabled true;
    }

    //...
}
```

系统会自动导入许多DataBinding包，如果需要使用到必须在dependencies中自己导入大致如下
```gradle
com.android.databinding:library:1.1
com.android.databinding:baseLibrary:1.1
com.android.databinding:adapters:1.1
```

## 使用 DataBinding

1. 简单使用

在布局文件为加入`<layout></layout>` 系统会根据布局文件名字生成对接的类文件