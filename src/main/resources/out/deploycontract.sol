pragma solidity ^0.5.16;

import './editedelection.sol/';

contract deploycontract{
    address public owner = msg.sender;
    editedelection public electioncontract;
    address[] public contract_address;
    constructor() public{
    }
    modifier onlyowner(){
        require(msg.sender==owner);
        _;
    }
    //給予權限
    function depoy_contract() public onlyowner{
        electioncontract = new editedelection(owner);
        //在子合約中的建構子要求發布時輸入管理者地址
    }
    //呼叫此方法就會發佈新的子合約
    //此方法限制管理者使用
    function return_address() public view returns(address){
        return address(electioncontract);
    }
    function record_contract() private onlyowner{
        contract_address.push(return_address());
    }
}
