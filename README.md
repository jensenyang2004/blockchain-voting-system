# DECENTRIALIZED VOTING SYSTEM
[![Build Status](https://travis-ci.org/web3j/web3j-maven-plugin.svg?branch=master)](https://travis-ci.org/web3j/web3j-maven-plugin)
[![codecov.io](https://codecov.io/github/web3j/web3j-maven-plugin/coverage.svg?branch=master)](https://codecov.io/github/web3j/web3j-maven-plugin?branch=master)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)


# 程式碼原始路徑
  ## 投票合約程式碼路徑:
  ### 複製檔路徑:blockchain-voting-system/投票合約.sol
 
  ## 母合約程式碼路徑:
  ### 複製檔路徑:blockchain-voting-system/母合約.sol

  ## 主系統程式碼路徑:
  ### 複製檔路徑:blockchain-voting-system/系統主程式碼.java







Web3j 原始碼的生成指令

![1_IVmYRWgQ4yw_aaUNJXdMfw](https://user-images.githubusercontent.com/82329310/122502299-f1dd1800-d028-11eb-9fe1-72233e3e3910.png)

系統操作流程

    母合約在整個系統中是必要的元素，在設計中，一個區塊鏈中(整個國家只會擁有一個區塊鏈)理論上只會存在一個母合約，而隨著投票的進行，投票合約會被母合約發佈，而母合約終究只有一個。

    下方流程都是在母合約發佈後才能進行的操作，要發佈母合約，必須透過Java程式來進行發佈，母合約發佈檔路徑 blockchain-voting-system/src/main/java/org/web3j/mavenplugin/Main2.java 


登入畫面
-------
![2021-06-27 (9)](https://user-images.githubusercontent.com/82329310/123538168-12624c00-d766-11eb-8a3d-95fed0ada35a.png)

    執行程式後會看到登入畫面，在文字框內輸入自己帳號的私鑰和地址便可登入。

合約發布
-------
![2021-06-16 (12)](https://user-images.githubusercontent.com/82329310/122497022-5004fd80-d01f-11eb-812d-2b501348c725.png)

    如果登入者微系統預設管理者的私鑰則會出現部屬合約的視窗，管理者輸入候選人的名字後發布合約(預設候選人數為三個)。

![2021-06-18 (2)](https://user-images.githubusercontent.com/82329310/122495379-81c89500-d01c-11eb-969b-37ab40ff9373.png)

    看到跳出視窗代表合約部屬成功

管理者畫面
-------
![2021-06-27 (3)](https://user-images.githubusercontent.com/82329310/123538182-28700c80-d766-11eb-9ec9-1bd1f72aa62e.png)

投票者畫面
-------
![2021-06-27 (5)](https://user-images.githubusercontent.com/82329310/123538192-31f97480-d766-11eb-8aaa-2d46800b755e.png)

    接著管理者可以看到管理者的操縱畫面，有start ballot 跟 end ballot兩個按鈕，管理者按下 start ballot 後投票者才能開始投票，當管理者按下結束投票，管理者視窗會跳出最終結果畫面。
    但如果投票人要看到投票結果要按下顯示選舉結果按鈕，如果投票已經結束，才會顯示出結果視窗。另外refresh按鈕可以讓管理者在部屬合約時更新管理者畫面。

結果畫面
-------
![2021-06-27 (8)](https://user-images.githubusercontent.com/82329310/123538199-3cb40980-d766-11eb-877e-61b04d12cabd.png)

    結果頁面有每個候選人獲取的票數和當選者的名字顯示在最上方。

Ganache-cli畫面
-------
    因為UI介面的ganache測試鏈出現問題，使用架構相同ganache-cli來當作測試鏈。
![1](https://user-images.githubusercontent.com/82329310/128651971-3c119968-a41a-4431-af2f-da837edb53b1.png)
![2](https://user-images.githubusercontent.com/82329310/128651977-a617425c-c26b-4e3b-8f41-f4a7dc01ead3.png)
![3](https://user-images.githubusercontent.com/82329310/128651983-088d6678-3d5b-46db-9109-258718d0dca8.png)

Ganache畫面
-----
    原本使用的ganache畫面
![image](https://user-images.githubusercontent.com/82329310/128652195-908ba5e0-4d9c-41e0-af61-9862d71ee7bd.png)


