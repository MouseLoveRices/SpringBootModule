import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { useParams } from 'react-router-dom'
import { Alert, Button, Container, FormGroup, Label, Table } from 'reactstrap';
import { deleteImage, getAllStudentDetail, resetStatusAndMessage, uploadImage } from '../../redux/studentSlice';
import axios from 'axios';

export default function StudentDetails() {
    const {id} = useParams();
    const[files,setFiles]=useState([]);
    const {studentDetails,message,error,status} = useSelector((state)=>state.student)
    const[images,setImages]=useState({})
    const [showMessage,setShowMessage] = useState(false)
    const dispatch = useDispatch();
    const handleChange = (e)=>{
        setFiles(e.target.files)
    }
    const handle_submit=async(e)=>{
        e.preventDefault();
        const formData = new FormData();
        for(let i = 0; i<files.length; i++){
            formData.append("file",files[i])
        }
        try {
            await dispatch(uploadImage({id,formData})).unwrap();
            dispatch(getAllStudentDetail(id))
        } catch (error) {
            console.error("Error uploading files",error)
        }
    }
  

    useEffect(() => {
      if (studentDetails && Array.isArray(studentDetails)) {
          studentDetails.forEach(item => {
              
              if (item && item.imageUrl) {               
                  fetchImage(item.imageUrl);
              } 
          });
      }
  }, [studentDetails])

    useEffect(()=>{
      dispatch(getAllStudentDetail(id))
    },[dispatch,id])

    const fetchImage = async(imageUrl)=>{
      try {
        const response =await axios.get(`http://localhost:8080/api/v1/admin/student/images/${imageUrl}`,{
          responseType:'blob' //dam bao phan hoi tra ve blob
        })
        const imageObjectURL = URL.createObjectURL(response.data)
        setImages(prev=>({...prev,[imageUrl]:imageObjectURL}))
      } catch (error) {
        console.log("Error fetching files", error)
      }
    }

    useEffect(()=>{
      if(status&&message){
        setShowMessage(true);

        const timer = setTimeout(()=>{
          setShowMessage(false)
          dispatch(resetStatusAndMessage());
        },2000)

        return () =>clearTimeout(timer)
      }
    },[status,message,dispatch])

    const handle_delete=(id)=>{
      dispatch(deleteImage(id))
    }

  return (
    <div>
      <h1>This is student with id: {id}</h1>
      <Container>
        {
          showMessage&&(
              <Alert color={status===200?"success":"danger"}>{message}</Alert>
          )
        }
        {
          error&&(
            <Alert color='danger'>
              <ul>
                {
                  error.map((error,index)=>(
                    <li key={index}>{error}</li>
                  ))
                }
              </ul>
            </Alert>
          )
        }
        <form onSubmit={handle_submit}>
            <FormGroup>
                <Label>Upload image    </Label>
                <input type='file' name='file' multiple onChange={handleChange}/>
                <input type='submit' value='Save' style={{background:"aqua",border:"1px solid", borderRadius:"5px",padding:"8px"}}/>              
            </FormGroup>
        </form>
      </Container>
        <Container>
            <Table hover>
                  <thead>
                    <tr>
                      <th>#</th>
                      <th>ID</th>
                      <th>Image</th>
                      <th>Delete</th>
                    </tr>
                    
                  </thead>

                  <tbody>
                    {
                      studentDetails&&studentDetails.map((item,index)=>(
                        <tr key={index}>
                            <td>{index+1}</td>
                            <td>{item.id}</td>
                            <td>
                              
                              <img src={images[item.imageUrl]} alt="Product" style={{width:'100px',height:'100px',objectFit:'cover'}}/>
                            </td>
                            <td>
                              <Button className="btn btn-danger" onClick={handle_delete(item.id)}>
                                  <i className='fa-solid fa-delete-left'></i>
                              </Button>
                            </td>
                        </tr>
                      ))
                    }
                  </tbody>
            </Table>
        </Container>
      
      
    </div>
  )
}
