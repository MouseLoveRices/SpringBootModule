import React, { useEffect, useState } from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Label, Input, FormGroup, Alert } from 'reactstrap';
import { addStudent, resetStatusAndMessage } from '../../redux/studentSlice';
import { useDispatch, useSelector } from 'react-redux';

export default function ModalsAdd() {
    const [modal, setModal] = useState(false);
    const dispatch = useDispatch();
    const toggle = () => setModal(!modal);
    const [student,setStudent]=useState({"ten":"Meo","thanhPho":"HCM","ngaySinh":"2003-12-26","xepLoai":"Gioi"})
    const {status, message,error} = useSelector(state => state.student)
    const[localStatus,setLocalStatus] = useState("")
    const[localMessage,setLocalMessage] = useState("")
    const[showMessage,setShowMessage] = useState(false)
    const handleAdd=()=>{
        toggle();
        dispatch(addStudent(student))
    }

    useEffect(() =>{
        if(status === 200){
            setLocalStatus(status)
            setLocalMessage(message)
            setShowMessage(true)
            const timer = setTimeout(()=>{
                setShowMessage(false)
                dispatch(resetStatusAndMessage())
            },4000)
            return ()=> clearTimeout(timer)
        }
    },[status,message,dispatch])

    const handleChange = (e) => {
        const { name, value } = e.target;
    
        // Handle date formatting for 'ngaySinh' input
        if (name === 'ngaySinh') {
          const formattedDate = dateToDDMMYYYY(value); // Call the new formatDate function
          setStudent(prevStudent => ({
            ...prevStudent,
            [name]: formattedDate
          }));
          return; // Exit the function after updating 'ngaySinh'
        }
    
        setStudent(prevStudent => ({
          ...prevStudent,
          [name]: value
        }));
      };
    
      // Function to format date in dd-mm-yyyy format
      const dateToYYYYMMDD = (date) => {
        const [day, month, year] = date.split('-'); // Split by '-'
        return `${year}-${month}-${day}`; // Reassemble in dd-mm-yyyy
      };

      const dateToDDMMYYYY = (date)=>{
        const [year, month, day] = date.split('-'); // Split by '-'
        return `${day}-${month}-${year}`; 
      }

    return (
      <div>
        <Button color="success" onClick={toggle}>
          Add Student
        </Button>
        <Modal isOpen={modal} toggle={toggle} >
        {error&&(
                       <Alert color='danger'>
                            <ul>
                                {error.map((err,index)=>(
                                    <li key={index}>{err}</li>
                                ))}
                            </ul> 
                        </Alert>                 
                )}
          <ModalHeader toggle={toggle}>Modal title</ModalHeader>
          <ModalBody>
            <FormGroup >
                <Label>Nhập họ và tên</Label>
                <Input onChange={handleChange} value={student.ten} type='text' name='ten' placeholder='2-50 kí tự'></Input>
                <Label>Nhập thành phố</Label>
                <Input onChange={handleChange} value={student.thanhPho} name='thanhPho' type='text' placeholder='Không để trống'></Input>
                <Label>Nhập ngày sinh</Label>
                <Input onChange={handleChange} value={dateToYYYYMMDD(student.ngaySinh)} type='date' name='ngaySinh' placeholder='yyyy-dd-mm'></Input>
                <Label>Chọn xếp loại</Label>
                <Input
                value={student.xepLoai}
                onChange={handleChange}
                name="xepLoai"
                type="select">
                <option>Gioi</option>
                <option>Trung binh</option>
                <option>Kha</option>                              
                </Input>
            </FormGroup>
            
          </ModalBody>
          <ModalFooter>
            <Button color="primary" onClick={handleAdd}>
              Thêm
            </Button>{' '}
            <Button color="secondary" onClick={toggle}>
              Cancel
            </Button>
          </ModalFooter>
        </Modal>
      </div>
    );
  }
  
