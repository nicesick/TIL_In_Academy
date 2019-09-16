# Android 진동, 소리

## I. vibrate & play

- vibrate 함수로 진동을 발생

```java
Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

if (Build.VERSION.SDK_INT >= 26) {
    vibrator.vibrate(VibrationEffect.createOneShot(1000,10));
} else {
    vibrator.vibrate(1000);
}
```



- play 함수로 소리를 발생

```java
Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), uri);
ringtone.play();
// 기본음
```

```java
MediaPlayer player = MediaPlayer.create(getApplicationContext(), R.raw.beep);
player.start();
// raw 에 미리 저장된 음
```



## II. Notification

- 버전에 굉장히 민감하기 때문에 섬세하게 사용해야 된데
- 현재 : 7.0 에서는 오류가 안나는데 8.1 에서는 오류가 남



- push 알림받기
  - 서버에 핸드폰을 등록
  - 구글 서버에 메시지를 보내면
  - 등록된 핸드폰에 메시지가 뿌려진다
- 단 Notification 은 핸드폰에서 따로 해줘야한다.