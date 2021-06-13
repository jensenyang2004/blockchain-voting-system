pragma solidity ^0.5.16;
contract election{
    struct voters{
        bool voted_if;
    }
    uint256 index=0;
    uint candidatecount;
    bool public ballotend=true;
    bool ifinput=false;
    address public owner=msg.sender;
    mapping (address=>voters) token;
    event voting(address addr,uint amount);
    struct nominees{
        uint id;
        string name;
        uint amount;
    }
    nominees[] public nominee;
    constructor() public{
       token[owner].voted_if=true; 
    }
    modifier onlyowner(){
        require(msg.sender==owner);
        _;
    }
    function endballot() public returns(bool){
        if(msg.sender==owner){
            ballotend=true;
        }else{
            revert("access denied");
        }
    }
    function input(string memory name_) public onlyowner {
        candidatecount++;
        nominee.push(nominees(candidatecount,name_,0));
        if(candidatecount>=2){
            ifinput=true;
        }
        
    }
    function startballot() public returns(bool){
        if(msg.sender==owner){
            if(ifinput==true){
                ballotend=false;
            }
        }else{
            revert("access denied");
        }
    }
   
    function showN(uint _index) public view returns(string memory){
        nominees storage show=nominee[_index];
        return(show.name);
    }
    function result(uint _index) public view onlyowner returns(uint){
         nominees storage show=nominee[_index];
         return(show.amount);
    }
    function finalresult(uint _index) public view returns(uint){
        if(ballotend==true){
            nominees storage show=nominee[_index];
            return(show.amount);
        }
    }
    function vote(uint _index) public{
        nominees storage show=nominee[_index];
        if(ballotend==false){
            while(token[msg.sender].voted_if==false){
                show.amount=show.amount+1;
                index++;
                token[msg.sender].voted_if=true;
            }
        }
    }
    function winner() public view returns(string memory){
        uint index1=0;
        for(uint i=0;i<candidatecount-1;i++){
            nominees storage show=nominee[i];
            nominees storage show1=nominee[i+1];
            if(show.amount>show1.amount){
                index1=i;
            }else{
                index1=i+1;
            }
        }
        nominees storage finalshow=nominee[index1];
        return(finalshow.name);
    }
}
