pragma solidity ^0.5.16;
contract election{
    struct voters{
        bool voted_if=false;
    }
    
    uint256 index=0;
    uint candidatecount=0;
    bool public ballotend=true;
    bool if_correct_input=false;
    address public owner_address=msg.sender;
    event voting(address addr,uint name);
    //  event 用於把合約內容記錄到日誌中
    // 宣告event：event name([argument, ...]) [anonymous]; 
    // 呼叫event ：emit name([argument, ...]);
    mapping (address=>voters) token;
    // 語法：mapping(_KeyType => _KeyValue)
    // 為每個可能的投票地址對應到voters這個結構
    
    
    struct nominees{
        uint id;
        string name;
        uint total_votes;
        //建立一個候選人結構
    }
    nominees[] public nominee_Array;
    //建立多個儲存候選人結構的陣列
    
    constructor() public{
       token[owner_address].voted_if=true; 
    }
    //使owner沒有投票權
    
    modifier onlyowner(){
        //modifier是修飾函數，用於前置條件檢查
        //語法：modifier name([argument, ...]) { ... _; ... }
        require(msg.sender==owner_address);
        _;
    }
    //建立限制只有owner可以呼叫的修飾語
    
    function endballot() public onlyowner returns(bool){
        if(msg.sender==owner_address){
            ballotend=true;
        }else{
            revert("access denied");
        }
    }
    //限制只有owner可以結束投票
    
    function add_candidates(string memory name_) public onlyowner {
        /*
        memory 通常用於修飾計算會用到的變數，變成傳遞「值」
        */
        candidatecount++;
       nominee_Array.push(nominees(candidatecount,name_,0));
        if(candidatecount>=2){
            if_correct_input=true;
        }
        //輸入候選人名稱建立候選人並存到候選人陣列
    }
    
    function startballot() public onlyowner returns(bool){
        if(msg.sender==owner_address){
            if(if_correct_input==true){
                ballotend=false;
            }
        }else{
            revert("access denied");
        }
        //開始投票，並確保至少有兩個以上的候選人
    }
    
    function showN(uint _index) public view returns(string memory){
        nominees storage show=nominee_Array[_index];
        /*
        storage 修飾存放最終狀態的地方，
        而這些地方就是用來呈現在區塊鏈裡狀態值，
        全域變數就會用 storage 來儲存。
        */
        return(show.name);
        //此方法可用編號來查詢候選人
    }
    
    function result(uint _index) public view onlyowner returns(uint){
            nominees storage show = nominee_Array[_index];
            return(show.total_votes);
    }//使中選會可以在投票期間查詢票數
    
    function finalresult(uint _index) public view returns(uint){
        if(ballotend==true){
            nominees storage show=nominee_Array[_index];
            return(show.total_votes);
        }
    }//投票結束後每個選民都可以查詢候選人的得票數
    
    function vote(uint _index) public{
        nominees storage show=nominee_Array[_index];
        if(ballotend==false){
            while(token[msg.sender].voted_if==false){
                show.total_votes=show.total_votes+1;
                index++;
                token[msg.sender].voted_if=true;
                emit voting(msg.sender , _index );
            }
        }
    }//此方法為投票動作，並限制每個人只能投一票，投完後 voted_if=true
    
    function winner_exhibit() public view returns(string memory){
        uint index1=0;
        for(uint i=0;i<candidatecount-1;i++){
            nominees storage show=nominee_Array[i];
            nominees storage show1=nominee_Array[i+1];
            if(show.total_votes>show1.total_votes){
                index1=i;
            }else{
                index1=i+1;
            }
        }
        nominees storage finalshow=nominee_Array[index1];
        return(finalshow.name);
    }//此方法顯示勝選者
}
