# Call Recorder (Android)

Auto-starts recording when a phone call becomes active and stops when it ends.
Recordings are saved as `.m4a` files in the app's private storage:
`Android/data/com.example.callrecorder/files/Music/CallRecordings/`.

## IMPORTANT: what it can actually record
On **Android 10+**, Google blocks normal apps from capturing the *other* party's
voice. This app tries `VOICE_CALL` (both sides) and automatically falls back to
`MIC` (your side; the other side is only captured on **speakerphone**, or if the
phone is **rooted** / the app is installed as a system app).

## How to get the APK without installing anything (cloud build)
1. Create a free account at github.com.
2. Make a new **empty** repository (any name, e.g. `call-recorder`).
3. Upload every file and folder from this project into that repo
   (drag-and-drop in the GitHub web UI, or use `git push`). Keep the folder
   structure exactly as-is.
4. Go to the repo's **Actions** tab. The build runs automatically on upload
   (or click the "Build APK" workflow -> "Run workflow").
5. When it finishes (green check, ~3-5 min), open the run and download the
   **call-recorder-debug-apk** artifact at the bottom. Unzip it to get
   `app-debug.apk`.

## Install on your phone
1. Copy `app-debug.apk` to your phone.
2. Tap it; allow "install from unknown sources" when prompted.
3. Open the app, tap **Grant Permissions**, allow all.
4. Make a test call. Look for the "Recording call" notification, then find the
   `.m4a` file using a file manager at the path above.

## Legal note
Call-recording consent laws vary by country/state and some require ALL parties
to agree. Check your local law before relying on this.
