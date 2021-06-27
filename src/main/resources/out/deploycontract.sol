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
    function depoy_contract() public onlyowner{
        electioncontract = new editedelection(owner);
    }
    function return_address() public view returns(address){
        return address(electioncontract);
    }
    function record_contract() private onlyowner{
        contract_address.push(return_address());
    }
}