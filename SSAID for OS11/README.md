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