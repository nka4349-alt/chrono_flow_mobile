# 04 Current Web Contract

## HTML routes
- `GET /`
- `GET /login`
- `POST /login`
- `DELETE /logout`
- `GET /signup`
- `POST /signup`

## JSON routes (confirmed from current routes.rb)

### Users
- `GET /api/users`

### Events
- `GET /api/events`
- `GET /api/events/:id`
- `POST /api/events`
- `PATCH /api/events/:id`
- `DELETE /api/events/:id`
- `POST /api/events/:id/share_to_groups`
- `POST /api/events/:id/add_to_my_calendar`

### Event share requests / notifications
- `GET /api/event_share_requests`
- `PATCH /api/event_share_requests/:id`
- `POST /api/events/:event_id/share_requests`
- `GET /api/notifications`
- `PATCH /api/notifications/:id/read`

### Groups
- `GET /api/groups`
- `POST /api/groups`
- `GET /api/groups/:id`
- `PATCH /api/groups/:id`
- `DELETE /api/groups/:id`
- `PATCH /api/groups/:id/reorder`
- `GET /api/groups/:id/events`
- `GET /api/groups/:id/members`
- `POST /api/groups/:id/invite_friends`
- `PATCH /api/groups/:group_id/members/:user_id/role`

### Friends / direct chat
- `GET /api/friends`
- `GET /api/friend_requests`
- `POST /api/friend_requests`
- `PATCH /api/friend_requests/:id`
- `POST /api/direct_chats`
- `GET /api/direct_chats/:id/chat_messages`
- `POST /api/direct_chats/:id/chat_messages`

### Chat messages
- `GET /api/events/:event_id/chat_messages`
- `POST /api/events/:event_id/chat_messages`
- `GET /api/groups/:group_id/chat_messages`
- `POST /api/groups/:group_id/chat_messages`

## モバイル v1 で特に触る画面
- `/`
- `/login`
- カレンダー画面上の JS モーダル
- グループ / メンバー drawer
- チャット bottom sheet
