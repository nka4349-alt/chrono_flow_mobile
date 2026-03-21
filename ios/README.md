# iOS README

## 目的
ChronoFlow の iPhone / iPad 向け Hotwire Native shell を作る。

## 作り方の基本
1. Xcode で新規 iOS App を作る
2. Product Name を `ChronoFlow` にする
3. Bundle ID を `com.nka4349.chronoflow` にする
4. Hotwire Native iOS を package dependency として追加する
5. `AppDelegate` と `SceneDelegate` をこのリポジトリ内の example に寄せる
6. `path-configuration.json` を app bundle に含める
7. 実機または Simulator で `/login` → `/` を確認する

## このフォルダに置いてあるファイル
- `Config.example.plist`
- `AppDelegate.example.swift`
- `SceneDelegate.example.swift`
- `path-configuration.json`

## 実装メモ
- v1 では startLocation を `https://chrono-flow-mvp.onrender.com` にする
- ログインは Web のまま使う
- path configuration の remote 配信は、Rails 側に `public/configurations/ios_v1.json` を置いてから有効化する
- Web 側で複雑な JS モーダルを使っているので、まずはすべて web destination でよい

## 最初の確認
- 起動できる
- `/login` が表示される
- ログインできる
- ホーム画面が表示される
- カレンダーが崩れず表示される
- グループ drawer とチャットが使える
