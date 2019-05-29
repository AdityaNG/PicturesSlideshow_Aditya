# Dobby Pictures.md
- An Android App (API Level 19 or above) to Display Downloaded Images
- Displays image loop
- Checks for update at end of loop
- Chose from multiple channels
- Picture's time configuration pulled from Firebase servers.

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
<br><img src="https://previews.dropbox.com/p/thumb/AAeWOrDBISvfuP40RkmvPwi3gzrECtwF_4u_LBIx3FwVdSUJfbpAZ_YQwbisNCwNF3gBXUYvGyBrQJnOcVKXgRd-pzhkpyix_RyoO2D3dg7wjT15X8QQVKH2CtWyTu_4UXFtik27xQiWDqsV_gvV0Hekrai8On7EFYwsUg6W4lMYr_LfqteXBO8H8-ea0B0T2mqnCsxXr8zIxEYcp2mlZ9DATSSTaQrDIIGVakWuA-kZ0EFosclmrYx2-0P8m-5xMHeatTA9l6w2TEYsUfuIAofmFNPV7Bvaea5Oht0YXD5m1IaNOxFgvHOnsDhol6fE7m4Vra330FNB67cNJqil5N4j/p.png?fv_content=true&size_mode=5" style="width:200px;"/>


- Fullscreen Activity
1. <img src="https://previews.dropbox.com/p/thumb/AAeeaceaKF1Uo0t59U7L7Q3lpN47t5EcCQAIuiMX789WxcBsVrsJ5oQ7oZ5Vz_GjSAJunh2lTKe0M2PHO5qSM_N0eZ6hfKRLtqxaeJRaVFglK1WeCTo5PY96DXBvgFyfZLhNaZWv0FkfsdXG34GD1NS7hck_6ZLNbHw3PTHYYEIqTvZhhWlL81r8zexWEg_B5Gv10w5UAmcgiNFJWIPC6KnHVHASp_eGBqGueM5UC1nSphs7J3hkXE3aPuBuua6cINw_3J-4gbtIHDNKoSqBjQ197BiTTAxXcrA9DdQpUo6LKgLNQgdv_tKnN9DPmoPxcFVgl6AdXYz3UpB71mAv5223/p.png?size=1600x1200&size_mode=3" style="width:200px;"/>
2. <img src="https://previews.dropbox.com/p/thumb/AAfV7PjNszrDlUVn2jLIqQBtRMp1Hn0ru7NHOpm5oMTLpnyo0J7e_8KdhBYIRrl9v9SHQubKP88hKT9Is7XGshbCkd2vwEkzYQsZ4nAY9hwdSLMoyFke_ZaEs8nPbnHfLKJqigjUXI2Hm5ggMrzMe7asiEkSZ84PXOvTDtL0H-qMyuKEQ0pleajHAfGizQBqbT_Ry6z6fYWIBwL_P6dVstKRczaEgyCHDw3XjVUzEj7gPvRtdkEclzeSPBKCryqo-UNa9X6ed8atTt_MpdCGXahyVP4IJxkgyY4s1MUCdpU5OMJBXN02gSrAWxcklKbhh51FH25N2eQJ0yj_xuIqRifG/p.png?fv_content=true&size_mode=5" style="width:200px;"/>
3. <img src="https://previews.dropbox.com/p/thumb/AAeVdZfDxPUuA3FY7MnZ_3MinpXO5fp-bDGv5axU4srMQCFz4UK9KFdwXPdOOGzICWg9tyEmb2yQuxunREkEBoWO1hSLUlhH28luT-4M0nZoFVTy3WJ-IhrDH0NGzuHgdoU6k89eLxbbCDM57LvEdtwZ1Sp5L8rCDNMx0SFe0ay3YGS1NPM6c31dgmzSzZ3W1tJOQqOfx5kWYu7CoDZj9sxsiGgb8i55iq70PKZaMv7b9G2HaybioIssb5fLxEchXWwXQeZha5cNgKrdSxuQl0Tmn2kFte3OThUuMYU2uvewj6LcEC1aXI8vlmCVYPOSsC6VGVt_PIps8ELTj0cAWRD9/p.png?fv_content=true&size_mode=5" style="width:200px;"/>


- App Icon
<br><img src="https://ucf84c43f6c205b16b7474e5ea56.previews.dropboxusercontent.com/p/thumb/AAfZ3D6FSWkDB5RQDVTL9AHh4zZbtNochcAagw8WxFcrboHI2lhGEbEeLx-JBjEFRDG5Bnt8f8HjpWSY4kfXZXSKEKHTtyAUVy9g_IBMRjHkCe6-RPs_RGK7UhbpATiZPHL38HbDOXVfJlovofKpvxKSI9AzFMq59xb3X1zT9ziCyYoK0xNqy1Z2tNXZX9Y0dBfzrqvdxpD3OVpy6z7QKIBCLJiSHyCskwl6PQxgYZDWBOZcIVk0hL8z0vjZJdkPJ13s71c_qnteVMN-rl1c8ZoCoBhHIhwDQYRyszdg4QfkEm4L3i-y9TY0rS-1iauXbtBLsXT8jmB2EMExFB4ieVTua87tPHA0Ndp8CRSNNZpeSMglyWzp8v2EaxM04KvpKL4/p.png?fv_content=true&size_mode=5" style="width:150px;"/>


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