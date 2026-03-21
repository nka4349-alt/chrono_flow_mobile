# ChronoFlow Mobile

ChronoFlow の **iOS / Android 向けモバイルシェル** 用リポジトリです。

このリポジトリは **新しい Rails バックエンドではありません**。
本体は既存の `chrono_flow_mvp` で、Render 上の Rails アプリをそのまま使います。
`chrono_flow_mobile` は、既存 Web をスマホアプリとして配布するための **Hotwire Native ベースのクライアント入れ物** です。

## この README が前提にしている ChronoFlow 現状

現在の `chrono_flow_mvp` から確認できる主機能は次のとおりです。

- ログイン / サインアップ
- 個人カレンダー（month / week / day）
- グループツリー表示
- グループイベント
- イベント作成・編集・削除
- イベントの `location / description / color`
- グループメンバー表示
- フレンド一覧 / フレンドリクエスト
- グループチャット / イベントチャット / 1:1 チャット
- 通知 / イベント共有リクエスト

## 目的

モバイル v1 の目的は、**今ある Web 体験を作り直さずに、配布可能な iOS / Android アプリとして包むこと**です。

- backend: `chrono_flow_mvp`
- mobile shell: `chrono_flow_mobile`
- ベース URL: `https://chrono-flow-mvp.onrender.com`
- 最初の起点: `/`
- 未ログイン時: `/login`

## v1 のゴール

1. iPhone / Android で ChronoFlow を起動できる
2. ログイン後にホーム画面へ入れる
3. カレンダー、グループ、チャットが最低限使える
4. TestFlight / Google Play のベータ配布まで進められる
5. ストア審査に必要な準備をチェックリスト化する

## リポジトリ構成

```text
chrono_flow_mobile/
  README.md
  .gitignore
  config/
    environments.example.json
  docs/
    01_product_context.md
    02_architecture.md
    03_auth_and_session_notes.md
    04_current_web_contract.md
    05_phase1_backlog.md
    06_test_scenarios.md
    07_server_side_tasks_for_mobile.md
    app-store-notes.md
    play-console-notes.md
    release-checklist.md
    reviewer-demo-account.template.md
    store-metadata-ja.md
    decision-log-template.md
  ios/
    README.md
    Config.example.plist
    AppDelegate.example.swift
    SceneDelegate.example.swift
    path-configuration.json
  android/
    README.md
    local.properties.example
    MyApplication.example.kt
    MainActivity.example.kt
    AndroidManifest.additions.xml
    activity_main.example.xml
    app/
      src/
        main/
          assets/
            json/
              android_v1.json
  server_examples/
    public/
      configurations/
        ios_v1.json
        android_v1.json
```

## まず固定する値

- App Name: `ChronoFlow`
- iOS Bundle ID: `com.nka4349.chronoflow`
- Android Package Name: `com.nka4349.chronoflow`
- Base URL: `https://chrono-flow-mvp.onrender.com`
- Remote iOS Path Config URL: `https://chrono-flow-mvp.onrender.com/configurations/ios_v1.json`
- Remote Android Path Config URL: `https://chrono-flow-mvp.onrender.com/configurations/android_v1.json`

## 先にやる順番

### Phase 0: Web 側の前提を整える

- [ ] Privacy Policy ページを Rails 側に作る
- [ ] 利用規約ページを Rails 側に作る
- [ ] アカウント削除導線を Rails 側に作る
- [ ] 審査用 demo account を用意する
- [ ] `public/configurations/ios_v1.json` と `public/configurations/android_v1.json` を設置する

### Phase 1: Native shell を作る

- [ ] iOS プロジェクト作成
- [ ] Android プロジェクト作成
- [ ] Hotwire Native 組み込み
- [ ] 起点 URL を本番 Render に向ける
- [ ] ログイン / ホーム / グループ / チャットを実機確認

### Phase 2: ベータ配布

- [ ] Android Internal Testing
- [ ] iOS TestFlight
- [ ] 審査メモと reviewer account を整備

## 実装上の重要メモ

- 認証は **Cookie セッション前提** でまず動作確認する
- `SessionsController#create` は通常の Rails フォーム送信なので、**CSRF token を壊さない**ことが大事
- API はログイン後の同一セッションで叩く想定
- v1 では、ログインもホームも **Web 側をそのまま使う**
- 外部リンクはアプリ外ブラウザへ出す方針

## このリポジトリの使い方

1. この雛形を `chrono_flow_mobile` として新規 repo に置く
2. `config/environments.example.json` をコピーして `config/environments.local.json` を作る
3. 先に `docs/07_server_side_tasks_for_mobile.md` を見て、Rails 側の必要タスクを洗い出す
4. iOS / Android README に沿って空プロジェクトを作る
5. 実機でログイン確認をする

## ローカル設定ファイル

```bash
cp config/environments.example.json config/environments.local.json
```

## 最低限の完成条件

- iPhone / Android 実機で起動できる
- ログインできる
- ホーム画面が出る
- グループ選択ができる
- チャット送信ができる
- ベータ配布用メタデータが埋まっている

## 次に読むファイル

- `docs/01_product_context.md`
- `docs/03_auth_and_session_notes.md`
- `docs/07_server_side_tasks_for_mobile.md`
- `ios/README.md`
- `android/README.md`
