# 02 Architecture

## 全体像

```text
iPhone / Android App (chrono_flow_mobile)
  └─ Hotwire Native shell
       └─ WebView / shared navigator
            └─ https://chrono-flow-mvp.onrender.com
                 ├─ HTML: /, /login, /signup
                 ├─ JSON: /api/events, /api/groups, /api/friends, ...
                 └─ PostgreSQL
```

## 責務分離

### chrono_flow_mvp
- 認証
- 画面 HTML
- JSON API
- DB
- 業務ロジック
- ストア審査向けの Privacy Policy / account deletion / demo account 導線

### chrono_flow_mobile
- ネイティブの起動体験
- iOS / Android への配布
- Cookie を維持したまま Web を表示
- 必要最小限の path configuration
- 外部リンクの扱い

## 初期方針
- まずは 1 つの navigator / 1 つの main stack
- 起動 URL は `/`
- 未ログインなら Rails が `/login` にリダイレクト
- ログイン以後は既存 Web UI を再利用

## 将来の拡張余地
- ネイティブ共有シート
- ネイティブ通知
- 特定画面だけ native screen 化
- deep link
- push notification
