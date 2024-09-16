import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/v1/admin';

export const getAlll=createAsyncThunk("student/getAll",async ({currentPage,limit},thunkAPI)=>{
    const url=BASE_URL+`/student/list?page=${currentPage}&size=${limit}`;
    try{
        const response=await axios.get(url);
        return response.data;
    }
    catch (error){
        return thunkAPI.rejectWithValue(error.response.data); // Trả về lỗi nếu có
    }
});

export const addStudent=createAsyncThunk("student/add",async(student,thunkAPI)=>{
    const url = `${BASE_URL}/student`
    try {
        const response = await axios.post(url,student);
        return response.data
    } catch (error) {
        return thunkAPI.rejectWithValue(error.response.data);
    }   
})

export const deleteStudent=createAsyncThunk("student/delete",async(id,thunkAPI)=>{
    const url = `${BASE_URL}/student/${id}`
    try {
        const response = await axios.delete(url)
        return response.data
    } catch (error) {
        return thunkAPI.rejectWithValue(error.response.data);
    }
})

export const updateStudent=createAsyncThunk("student/update", async({id,student},thunkAPI)=>{
    const url=`${BASE_URL}/student/${id}`
    try {
        const response = await axios.put(url,student)
        return response.data;
    } catch (error) {
        return thunkAPI.rejectWithValue(error.response.data);
    }
})

export const searchByName = createAsyncThunk("search/name", async (name,thunkAPI)=>{
    const url = `${BASE_URL}/student/search2?name=${name}`
    try {
        const res = await axios.get(url);
        return res.data;
    } catch (error) {
        return thunkAPI.rejectWithValue(error.response.data);
    }  
})

export const searchByYear=createAsyncThunk("student/year",async({startYear,endYear},thunkAPI)=>{
    const url = `${BASE_URL}/student/search4?startYear=${startYear}&endYear=${endYear}`
    try{
        const response = await axios.get(url)
        return response.data;
    }catch(error){
        return thunkAPI.rejectWithValue(error.response.data)
    }
})

export const uploadImage=createAsyncThunk("student/uploadImage",async({id,formData},thunkAPI)=>{
    const url = `${BASE_URL}/student/uploads/${id}`
    try {
        const response = await axios.post(url,formData,{
            headers:{
                'Content-type': 'multipart/form-data'
            }
        })
        return response.data;
    } catch (error) {
        return thunkAPI.rejectWithValue(error.respones.data);
    }
})

export const getImage= createAsyncThunk("student/getImage",async(imageName,thunkAPI)=>{
    const url =`${BASE_URL}/student/images/${imageName}`
    
    try {
        const response = await axios.get(url)
        return response.data;
    } catch (error) {
        return thunkAPI.rejectWithValue(error.respones.data);
    }
})

export const getAllStudentDetail = createAsyncThunk("student/getAllStudentDetail",async(id,thunkAPI)=>{
    const url = `${BASE_URL}/student/getAllImage/${id}`
    try {
        const response = await axios.get(url)
        return response.data;
    } catch (error) {
        return thunkAPI.rejectWithValue(error.response.data);
    }
})

export const deleteImage = createAsyncThunk('student/deleteImage',async(id,thunkAPI)=>{
    const url = `${BASE_URL}/student/images/${id}`
    try {
        const response = await axios.delete(url)
        return response.data;
    } catch (error) {
        return thunkAPI.rejectWithValue(error.response.data)
    }
})

const studentSlice=createSlice({
    name:"student",
    initialState:{
        searchResult:[],
        students:[],
        totalPages:10,
        status:'idle',
        error:null,
        message:"",
        studentDetails:null

    },
    reducers:{
        resetStatusAndMessage: (state)=>{
            state.status = null;
            state.message = "";
            state.error = null;
        }
    },
    extraReducers:(builder)=>{
        builder
        .addCase(getAlll.fulfilled,(state,action)=>{
            state.students=action.payload.data.studentResponseList
            state.totalPages=action.payload.data.totalPages
        })
        .addCase(addStudent.fulfilled,(state,action)=>{
            state.status = action.payload.status
            state.message = action.payload.message
            state.students = [...state.students, action.payload.data]
        })
        .addCase(addStudent.rejected, (state,action)=>{
            state.status=action.payload.status
            state.message = action.payload.message
            state.error = action.payload.data
        })
        .addCase(deleteStudent.fulfilled,(state,action)=>{
            state.status = action.payload.status
            state.message = action.payload.message
            state.students = state.students.filter(student=> student.id!== action.payload.data)
        })
        .addCase(deleteStudent.rejected, (state,action)=>{
            state.status = action.payload.status
            state.message = action.payload.message
            state.error = action.payload.data
        })
        .addCase(updateStudent.fulfilled, (state, action) => {
            state.status=action.payload.status
            state.message=action.payload.message
            state.students = state.students.map(student =>
              student.id === action.payload.data.id ? action.payload.data : student
          );
          })
        .addCase(updateStudent.rejected, (state, action) => {
            state.status=action.payload.status
            state.message=action.payload.message
            state.error=action.payload.data
        })
        .addCase(searchByName.fulfilled,(state,action)=>{
            state.searchResult =action.payload.data;
        })
        .addCase(searchByYear.fulfilled,(state,action)=>{
            state.students = action.payload.data;
            state.status =action.payload.status;
        })
        .addCase(searchByYear.rejected,(state,action)=>{
            state.status =action.payload.status
            state.message = action.payload.message
            state.error = action.payload.data;
        })
        .addCase(uploadImage.fulfilled,(state,action)=>{
            state.status =action.payload.status
            state.message = action.payload.message     
        })
        .addCase(getAllStudentDetail.fulfilled,(state,action)=>{
            state.status =action.payload.status
            state.message = action.payload.message    
            state.studentDetails = action.payload.data; 
        })
        .addCase(getAllStudentDetail.rejected,(state,action)=>{
            state.status =action.payload.status
            state.message = action.payload.message   
            state.error = action.payload.data;  
        })
        .addCase(deleteImage.fulfilled,(state,action)=>{
            state.status = action.payload.status
            state.message = action.payload.message
        })

        
    }
})
export const {resetStatusAndMessage} = studentSlice.actions
export default studentSlice.reducer