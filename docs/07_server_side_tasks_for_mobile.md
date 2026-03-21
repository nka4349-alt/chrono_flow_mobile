# 07 Server-side Tasks For Mobile

このファイルは `chrono_flow_mvp` 側で先にやると、モバイル化が楽になる項目です。

## 必須

### 1. Privacy Policy
- `/privacy` を作る
- ストア提出用 URL として固定公開する

### 2. Terms
- `/terms` を作る
- ストア説明欄やアプリ内導線から飛べるようにする

### 3. Account deletion
- ログイン済みユーザーが自分で退会できる導線を作る
- Apple 審査では account creation を提供する場合、account deletion も必要になりやすい

### 4. Demo account
- reviewer がログインできる account を 1 つ以上固定で準備する

### 5. Remote path configuration
`public/configurations/` に次を置く想定。

- `public/configurations/ios_v1.json`
- `public/configurations/android_v1.json`

この zip には `server_examples/public/configurations/` として雛形を同梱している。

## 推奨
- アプリ名 / ロゴ / OG 画像を確定する
- サポート連絡先ページを作る
- `/healthz` のような軽いヘルスチェック URL を持つ
- ユーザー削除時のデータ保持方針を文書化する
