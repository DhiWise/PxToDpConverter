## PxToDp Converter

PxToDp converter creates dimension files to make your UI responsive across all the devices. Simply Input the width and height of your app screen design, and have your responsive UI work on any phone and tablet within seconds.
## How does it work?
PxToDp converter's advanced algorithm auto generates the required responsive UI framework according to developers requirements.
 - Right click on Res folder then select PxToDp plugin

![PxToDp Converter](screenshot/pxtodp.gif)

 - In the PxToDp converter, input your app frame design viewport width and height.
   
![PxToDp Converter](screenshot/img_pxtodp.png)

 - Taking your input, our Responsive PxToDp converter will create the dimes xml file.
 - The file will have required inputs for pxh (pixel horizontally) and pxv (pixel vertically).
 - All the mobile design dimes xml files will be generated in your code.


## Example

| Your regular design xml file </br>(DP/SP/PX)                                                                                                                                                                                                                                                                                                            | Integration of pxh & phv in your code</br>(PxToDp converter)                                                                                                                                                                                                                                                                                                                                         |
|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| <TextView</br>android:id="@+id/txtSkip"</br>android:layout_width="wrap_content"</br>android:layout_height="wrap_content"</br>android:layout_gravity="end"</br>android:layout_marginStart="32dp"</br>android:layout_marginTop="66dp"</br>android:layout_marginEnd="32dp"</br>android:alpha="0.4"</br>android:gravity="center"</br>android:text="Skip" /> | <TextView</br>android:id="@+id/txtSkip"</br>android:layout_width="wrap_content"</br>android:layout_height="wrap_content"</br>android:layout_gravity="end"</br>android:layout_marginStart="@dimen/_32pxh"</br>android:layout_marginTop="@dimen/_66pxv"</br>android:layout_marginEnd="@dimen/_32pxh"</br>android:alpha="0.4"</br>android:gravity="center"</br>android:text="Skip" /> |
