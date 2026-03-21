# Android README

## 目的
ChronoFlow の Android 向け Hotwire Native shell を作る。

## 作り方の基本
1. Android Studio で新規プロジェクトを作る
2. Template は `Empty Views Activity`
3. Package Name は `com.nka4349.chronoflow`
4. minimum SDK は 28 以上
5. Kotlin DSL を使う
6. Hotwire Native Android を組み込む
7. `android_v1.json` を assets に含める
8. `MainActivity` / `Application` を example に寄せる

## このフォルダに置いてあるファイル
- `local.properties.example`
- `MyApplication.example.kt`
- `MainActivity.example.kt`
- `AndroidManifest.additions.xml`
- `activity_main.example.xml`
- `app/src/main/assets/json/android_v1.json`

## 実装メモ
- v1 では navigator は 1 本でよい
- startLocation は `https://chrono-flow-mvp.onrender.com`
- path configuration の remote 配信は Rails 側の `public/configurations/android_v1.json` を用意してから有効化する
- ログインは Web のまま使う

## 最初の確認
- 起動できる
- `/login` が表示される
- ログインできる
- ホーム画面が表示される
- カレンダーが表示される
- チャット送信までできる
