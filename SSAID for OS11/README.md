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
