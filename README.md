This is an example app which use data binding to illustrate a dynamic and inter-dependent GUI.

![image](https://cloud.githubusercontent.com/assets/990654/26285791/cf202b3a-3e56-11e7-9f58-5f47db47af71.png)

- UI elements are hidden/shown dependent on the radio button states
- There is delayed loading of data ("# of logged in users") which illustrate network requests and async UI update

Originally written as support code for the blog post named [Android Databinding: Goodbye Presenter, Hello Viewmodel!](http://tech.vg.no/2015/07/17/android-databinding-goodbye-presenter-hello-viewmodel/).
See tags [mvp](https://github.com/Nilzor/mvp-to-mvvm-transition/tree/mvp) and [mvvm](https://github.com/Nilzor/mvp-to-mvvm-transition/tree/mvvm) referred to by that post.

Later expanded upon to include a more complete Databinding example with full **two-way databinding** of EditText-fields at the
[two-way-implicit](https://github.com/Nilzor/mvp-to-mvvm-transition/tree/two-way-implicit) tag,
or manual read-back if you prefer that by the [two-way-explicit](https://github.com/Nilzor/mvp-to-mvvm-transition/tree/two-way-explicit) tag.

The final addition is **viewmodel retention** upon device rotation in later commits of branch [two-way](https://github.com/Nilzor/mvp-to-mvvm-transition/tree/twoway),
 and also experimentation with using the `LiveData` and `ViewModelProviders` classes of the [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)
 tool box  in the branch [androidarch](https://github.com/Nilzor/mvp-to-mvvm-transition/tree/androidarch)


