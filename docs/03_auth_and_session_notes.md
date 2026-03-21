# 03 Auth and Session Notes

## 現在の Rails 認証の前提

- `session[:user_id]` を使う Cookie セッション
- `SessionsController#create` は通常の Rails フォーム送信
- `ApplicationController` は未ログイン時に HTML では `/login` へリダイレクト
- `/api/*` は未ログイン時に JSON 401 を返す

## モバイルでの注意点

### 1. ログインを API 化しない
最初は `/login` をそのまま表示し、Rails フォーム送信でログインする。
これが一番壊れにくい。

### 2. CSRF を壊さない
Rails のフォーム送信は authenticity token が前提。
中途半端なネイティブ POST を挟むと壊れやすい。

### 3. 同一 base URL を使う
ログイン画面とホーム画面でドメインを混ぜない。

- 良い例: `https://chrono-flow-mvp.onrender.com`
- 避ける例: 開発用 URL と本番 URL の混在

### 4. セッション切れの扱い
アプリ内で 401 / 302 が出たときは `/login` に戻る流れを想定する。

## 初期実装ポリシー
- ログインは Web のまま
- Cookie 永続化は WebView / WKWebView の標準挙動を利用
- API 単独呼び出しではなく、ログイン後の通常遷移を前提にする
