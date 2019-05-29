# Dobby Pictures.md
- An Android App (API Level 19 or above) to Display Downloaded Images
- Displays image loop
- Checks for update at end of loop
- Chose from multiple channels
- Picture's time configuration pulled from Firebase servers.

- Download the APK from : https://github.com/AdityaNG/PicturesSlideshow_Aditya/blob/master/app-debug.apk
- ![](https://raw.githubusercontent.com/AdityaNG/PicturesSlideshow_Aditya/master/screenshots/icon.png)

# Getting Started
1. Clone the Repository
2. Open up Android Studio and hit "Import Android Studio Project'
3. Setup Firebase Database parameters
4. Compile and Upload to Android device


# Java Files Breakdown
### MainActivity
Landing Activity of the App
  Allows user to :
       1. Chose from multiple Channels
       2. Start Slideshow
       3. Checks for Read/Write Permissions
### FullscreenActivity
Fullscreen Activity for the Slideshow
 Allows user to :
       1. View the Slideshow
       2. Checks for updates for said show on the fly without interruptions
       3. Change channel by revisiting Main Activity

### FileCache
FileCache class handles the creation, access, delete request for the image cache files

### ImageLoader
Loads the Images, either from cache or Web
 Handles :
       1. Caching of Images
       2. Cache Naming Scheme
       3. Clear Cache function

### MemoryCache
Loads the Images, either from cache or Web
  Handles :
       1. Get Bitmap by Key
       2. Put Bitmap by Key
       3. Clear Cache function
### Utils
Utility for Copy Stream
 Necessary for the image downloading in ImageLoader Class
# Screenshots
- Main Activity
- ![](https://raw.githubusercontent.com/AdityaNG/PicturesSlideshow_Aditya/master/screenshots/0.png)

- Fullscreen Activity
- ![](https://raw.githubusercontent.com/AdityaNG/PicturesSlideshow_Aditya/master/screenshots/2.png)
- ![](https://raw.githubusercontent.com/AdityaNG/PicturesSlideshow_Aditya/master/screenshots/3.png)
- ![](https://raw.githubusercontent.com/AdityaNG/PicturesSlideshow_Aditya/master/screenshots/1.png)


- App Icon
- ![](https://raw.githubusercontent.com/AdityaNG/PicturesSlideshow_Aditya/master/screenshots/icon.png)


### Databse Design and Structure :
The Firestore is structured as follows : 

![](https://ucb0564f6dcc1af9b354966c5694.previews.dropboxusercontent.com/p/thumb/AAdorveg-v6LE_jIWOHh7wNkhFLYbT3W-4wM0U2aLvn_DWW9NaCtRUe6SxnFdo-vhrHlas1m7VDfSDJ6olSdOvaA4-faOrm3Xc8gtWLfZo268RLE90E-J2OkuME0AzpFu8BwHWUnz8N6iv2HvdT7WWz3JigsDlBkOtFz-W9rNMEr6OKWNxR-uJRD-kKryqORYnSdQH3AgfC9k1kr6TrP0Qabg04J9gzW5RbOAokZmhJt7qMTuuUn9db8stSqWGxptaDc9D4-ARLEXK15VEAl6EIJqeWqQzEKK_NeXzccY-wn2BtsSmNOR9FiCvPA8mUI52WSt__VakfyMwURvL_h6iN9kyH3gYzvtMq5Pj__1qQwpSopyk5TWHcgkcRoZey25Tm4h5e9QvmDDK33ZHkVmME6TzCv15EZpw2mz3mEjxDpvhsmN9NJ-U807x_8OV7Q2361xheSB1KDX56rV0QmlDGh4gBe1JtNK_umj8A0fUoEag/p.png?fv_content=true&size_mode=5)

The database can be querried using Rest by calling "https://firestore.googleapis.com/v1/projects/dobby-developer-task-android/databases/(default)/documents/channels/cherry_blossom"
Response : 

```json
{
  "name": "projects/dobby-developer-task-android/databases/(default)/documents/channels/cherry_blossom",
  "fields": {
    "pictures": {
      "arrayValue": {
        "values": [
          {
            "mapValue": {
              "fields": {
                "time": {
                  "integerValue": "10"
                },
                "url": {
                  "stringValue": "https://cdn.shopify.com/s/files/1/0022/0774/4115/products/shirofugen2_1024x1024@2x.jpg?v=1547238249"
                }
              }
            }
          },
          {
            "mapValue": {
              "fields": {
                "time": {
                  "integerValue": "15"
                },
                "url": {
                  "stringValue": "https://images.fineartamerica.com/images/artworkimages/mediumlarge/1/cherry-blossom-tree-kristine-mueller-griffith.jpg"
                }
              }
            }
          },
          {
            "mapValue": {
              "fields": {
                "time": {
                  "integerValue": "15"
                },
                "url": {
                  "stringValue": "https://i.pinimg.com/originals/f2/cb/22/f2cb22a0a8fa04a51a28d6802c19db8a.jpg"
                }
              }
            }
          },
          {
            "mapValue": {
              "fields": {
                "time": {
                  "integerValue": "12"
                },
                "url": {
                  "stringValue": "https://upload.wikimedia.org/wikipedia/commons/8/87/Cerisiers_en_fleurs_au_parc_de_Sceaux.JPG"
                }
              }
            }
          }
        ]
      }
    },
    "title": {
      "stringValue": "Cherry Blossom"
    }
  },
  "createTime": "2019-05-29T02:18:44.223863Z",
  "updateTime": "2019-05-29T11:08:54.683616Z"
}
```