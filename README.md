# Calendly Integration Slack Notifier
## Preparation
### Configuring Webhook Subscriptions in Calendly
Check https://developer.calendly.com/

#### Create a Subscription
https://developer.calendly.com/docs/webhook-subscriptions

```
curl \
--header "X-TOKEN: <token>" \
--data "url=https://blah.foo/bar" \
--data "events[]=invitee.created" \
https://calendly.com/api/v1/hooks
```
#### Remove a Subscription
https://developer.calendly.com/docs/delete-webhook-subscription

```
curl  -X DELETE --header "X-TOKEN: <your_token>" https://calendly.com/api/v1/hooks/<hook_id>
```

#### Get Subscriptions
https://developer.calendly.com/docs/get-list-of-webhook-subscriptions

```
curl --header "X-TOKEN: <your_token>" https://calendly.com/api/v1/hooks
curl --header "X-TOKEN: <your_token>" https://calendly.com/api/v1/hooks/<hook_id>
```

### Configuring Incoming Message Webhooks in Slack
See here: https://api.slack.com/messaging/webhooks


## Required Configuration
Create `application.yml` next to the jar file with your secret Slack Webhook URL.

```
slack.webhook.url.secret: /Txxxxxxxx/Bxxxxxxx/xxxxxxxxxxxxxxxxx
```

## Start Service
```
java -jar target/calendly-slack-notifier-VERSION.jar -Dspring.config.location=file:/PATHTO/application.yml
```

## API
### Request
```
POST SERVER_URL/calendly/deliver
```

### Payload Example
```json
{
  "event": "invitee.created",
  "payload": {
    "event_type": {
      "uuid": "XXX",
      "kind": "One-on-One",
      "slug": "45-minute-meeting",
      "name": "45 Minute Meeting ",
      "duration": 45,
      "owner": {
        "type": "users",
        "uuid": "XXX"
      }
    },
    "event": {
      "uuid": "XXXXX",
      "assigned_to": [
        "Daisy Duck"
      ],
      "extended_assigned_to": [
        {
          "name": "XXXXX",
          "email": "XXXXX",
          "primary": true
        }
      ],
      "start_time": "2020-08-18T09:00:00+02:00",
      "start_time_pretty": "09:00am - Tuesday, August 18, 2020",
      "invitee_start_time": "2020-08-18T09:00:00+02:00",
      "invitee_start_time_pretty": "09:00am - Tuesday, August 18, 2020",
      "end_time": "2020-08-18T09:45:00+02:00",
      "end_time_pretty": "09:45am - Tuesday, August 18, 2020",
      "invitee_end_time": "2020-08-18T09:45:00+02:00",
      "invitee_end_time_pretty": "09:45am - Tuesday, August 18, 2020",
      "created_at": "2020-07-26T17:22:44+02:00",
      "location": "https://my.zoom.us/j/XXXX?pwd=XXXX",
      "canceled": false,
      "canceler_name": null,
      "cancel_reason": null,
      "canceled_at": null
    },
    "invitee": {
      "uuid": "XXXX",
      "first_name": null,
      "last_name": null,
      "name": "Dagobert Duck",
      "email": "XXXX@XXXX.com",
      "text_reminder_number": null,
      "timezone": "Europe/Berlin",
      "created_at": "2020-07-26T17:22:44+02:00",
      "is_reschedule": false,
      "payments": [],
      "canceled": false,
      "canceler_name": null,
      "cancel_reason": null,
      "canceled_at": null
    },
    "questions_and_answers": [
      {
        "question": "Company",
        "answer": "Tresor Inc."
      }
    ],
    "questions_and_responses": {
      "1_question": "Company",
      "1_response": "Tresor Inc."
    },
    "tracking": {
      "utm_campaign": null,
      "utm_source": null,
      "utm_medium": null,
      "utm_content": null,
      "utm_term": null,
      "salesforce_uuid": null
    },
    "old_event": null,
    "old_invitee": null,
    "new_event": null,
    "new_invitee": null
  },
  "time": "2020-07-26T15:22:45Z"
}
```
