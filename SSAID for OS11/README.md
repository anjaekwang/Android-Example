# OS11 관련, 단말 구분 위한 SSAID TEST Code


현재 이용하고있는 단말구별 식별자
1. SIM이 존재하다면 MSISDN (전화번호)                       
2. IMEI                                                    
3. MAC addr                                                 


## 버전 이슈사항
- Android Q(v10)에서 IMEI 접근 제한
- Android R(v11)에서 MAC addr 접근 제한


## 대안
SSAID 이용   
수명 : 디바이스 초기화까지


## 참고사항
Android Id는 OS 8.0 (API 레벨 26) 이상 버전의 플랫폼에서 앱 서명 키, 사용자, 기기의 각 조합에 고유 한 64 비트 숫자 (**결국은 16 진수 문자열** 로 표시됨). 
기기에서 초기화를 수행하거나 APK 서명 키가 변경되면 값이 변경 될 수 있다
https://developer.android.com/reference/android/provider/Settings.Secure.html#ANDROID_ID


---

# 새로운 단말 식별자 자료조사



|  |범위    | 수명| 리턴값 길이| 비고 |
| :-------------: | :------------- | :------------- | :-------------: | :------------- |
| SSAID       | 앱그룹 (OS8 이상)<br/> 디바이스 (OS8 미만) | 디바이스 초기화 |16자리||
| 광고ID(ADID)|디바이스|디바이스 초기화<br/>광고ID 재설정(사용자)| 36자리| Google Play가없는 디바이스에서 사용불가|
|MediaDrm|디바이스|L1 : 없음<br/> L3 : 디바이스 초기화 | 44자리|OS4.3이상 및 DRM 모듈이 있어야 가능<br/>(Google Play)|


---

## SSAID

### 1. 리턴길이
원래는 32bit, Hex로 나오나 SSAID를 가져오는 내부 코드를 살펴 보면 아래와 같다.
~~~  
SecureRandom random = new SecureRandom();
String newAndroidIdValue = Long.toHexString(random.nextLong());
~~~
toHexString에서 항상 16자리 String이 나오도록 0이 패딩되므로

**SSAID는 항상 16자리의 String으로 return 된다.**

#### 참고자료
- https://developer.android.com/reference/android/provider/Settings.Secure.html#ANDROID_ID
- https://stackoverflow.com/questions/29889880/secure-android-id-length




### 2. OS별 차이

SSAID API는 API level3에서 추가되었다.

#### OS8 미만

사용자가 기기를 처음 설정할 때 임의로 생성되는 숫자로 사용자 기기의 수명 동안 일정하게 유지된다.  
사용자가 여러 명 있는 기기에서는 각 사용자가 완전히 별개의 기기로 나타나기 때문에  
**Android ID 값은 각 사용자에게 고유하다.**


#### OS8 이상

사용자뿐 아니라 앱 서명 키별로 범위가 지정하게된다.  
따라서 동일한 기기에서 실행 중인  
**다른 서명 키가 있는앱은 동일한 Android ID나오지 않는다.**
**재설치나 재부팅에도 일정하게 나온다.**



##### 참고자료
- [개인정보 보호정책](https://developer.android.com/about/versions/oreo/android-8.0-changes?hl=ko)
- [adb shell settings get secure android_id로 OS8에서 ANDROID_ID를 얻을 수 없다.](https://stackoverflow.com/questions/47551306/android-id-different-in-abd-and-in-code-on-api-26)
- 뇌피셜 :: OS8에선 기존 global settings 값에서 서명키를 조합해서 나오지 않을까라는 생각


### 3. 샤오미폰 SSAID 테스트 결과
- 동일한 앱 다른 서명키 : SSAID 다름
- 다른 앱 같은 서명키   : SSAID 같음  
