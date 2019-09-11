# Android 멀티미디어

## I. 오디오

- res > New > Folder > Raw Resources Folder
  - 이 폴더가 동영상, mp3 파일들을 관리하는 폴더다



```java
private MediaPlayer mediaPlayer;
private int position = 0;

private void releaseMedia() {
    if (mediaPlayer != null) {
        mediaPlayer.release();
    }
}

private void playAudio() {
    releaseMedia();

    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.kalimba);
    // kalimba 가 대문자면 인식을 못한다. ㅡㅡ
    mediaPlayer.start();
    // 이상하게 prepare 함수를 쓰니까 터졌다..?
}
```



## II. 동영상

- videoView 를 이용해서 구현한다.

```java
MediaController mc = new MediaController(this);
videoView.setMediaController(mc);

...

Uri uri = Uri.parse("android.resource://"
                    + getPackageName()
                    + "/"
                    + R.raw.sample_video);

videoView.setVideoURI(uri);
videoView.requestFocus();
videoView.start();
```



## III. 유투브

- app > libs 에 외부 라이브러리를 넣어준다.
- 오픈소스가 아닌 유투브같은 라이브러리들을 따로 넣어주어야 한다.



## IV. 위치 & 지도

- 위치를 찾는 방법
  - GPS
  - 3G, 4G, 5G 데이터 기지국의 삼각기법
  - 와이파이 위치를 미리 저장해서 위치를 측정



- 매니페스트
  - ACCESS_FINE_LOCATION
- 위치찾는 함수를 사용한다

```java
public void startLocationService() {
    LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

    try {
        Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            String message = "최근 위치 -> Latitude : " + latitude + "\nLongitude:" + longitude;

            textView.setText(message);
        }

        GPSListener gpsListener = new GPSListener();
        long minTime = 10000;
        float minDistance = 0;

        manager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            minTime, minDistance, gpsListener);

        Toast.makeText(getApplicationContext(), "내 위치확인 요청함",
                       Toast.LENGTH_SHORT).show();

    } catch(SecurityException e) {
        e.printStackTrace();
    }
}

class GPSListener implements LocationListener {
    public void onLocationChanged(Location location) {
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();

        String message = "내 위치 -> Latitude : "+ latitude + "\nLongitude:"+ longitude;
        textView.setText(message);
    }

    public void onProviderDisabled(String provider) { }

    public void onProviderEnabled(String provider) { }

    public void onStatusChanged(String provider, int status, Bundle extras) { }
}
```



## V. 지도

- Google Play Image 안에 지도 API 가 있다.

- fragment 에 지도를 뿌려줄 거다!



- 순서

  - 구글 key 를 만든다.

    - console.deverlopers.google.com
    - 사용자 인증 정보
    - android 앱 전용 key 만들기
      - 이 키를 사용하는 애가 누구냐...?
      - Program files\java\jdk.*\bin\keytool 을 이용해서 디지털 지문을 뽑아낸다.
      - 해당 컴퓨터는 해당 키와 맵핑된다.
      - 해당 컴퓨터에서만 키값으로 개발할 수 있다!
    - key 값을 google_maps_api 에 넣는다.

  - 프로젝트를 empty 가 아니라 google Map 으로 만든다.

  - build.gradle 에 구글 서비스를 추가한다.

    - ```gradle
      implementation 'com.google.android.gms:play-services-maps:16.0.0'
      ```

  - Manifest 파일에 가서 두가지를 추가한다.

    - ```xml
      <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
      
      <meta-data    android:name="com.google.android.geo.API_KEY"    android:value="AIzaSyDzJHH4OvfccA065QkJXeWKrZdlLH-hSZU" />
      ```

    

  - UI 는 맵이 들어갈 부분에 fragment 를 추가한다.

    - ```xml
      <fragment    
      android:id="@+id/map"    android:layout_width="match_parent"    android:layout_height="match_parent"    class="com.google.android.gms.maps.SupportMapFragment">
      </fragment>
      ```

  - 이제 코드를 넣어서 기능을 추가하자

    - ```java
      private SupportMapFragment mapFragment;
      private GoogleMap map;
      
      ...
      
      mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
      
      mapFragment.getMapAsync(new OnMapReadyCallback() {    @Override
          public void onMapReady(GoogleMap googleMap) {        Log.d("Map", "지도 준비됨.");        
                                                              map = googleMap;
           map.setMyLocationEnabled(true);
           // 이렇게 사용자 위치를 받아 올 수 있다. 하지만 위도, 경도 값을 모르므로 사용자 위치를 기반으로 무언가를 하려면 위도, 경도 값을 받아와서 계산하는 절차가 필요하다.
                                                      }});
      
      try {    
          MapsInitializer.initialize(this);
      } catch (Exception e) {    
          e.printStackTrace();
      }
      
      // 퍼미션은 필수!
      ```