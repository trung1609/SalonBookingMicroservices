import React from 'react';
import {Button, CircularProgress, Grid, IconButton, TextField} from "@mui/material";
import {AddPhotoAlternate, Close} from "@mui/icons-material";
import {useFormik} from "formik";
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

const CreateServiceForm = () => {
    const formik = useFormik({
        initialValues: {
            name: "",
            image: "",
            description: "",
            price: "",
            duration: "",
            category: "",
        },
        onSubmit: () => {
            console.log("Submitting values: ", formik.values)
        }
    })
    return (
        <div className={'flex justify-center items-center'}>
            <form onSubmit={formik.handleSubmit} className={'space-y-4 p-4 w-full lg:w-1/2'}>
                <Grid container spacing={2}>
                    <Grid className={'w-24 h-24'} size={{xs: 12}}>
                        {false ? <div className={'relative border'}>
                                <img
                                    className={'w-24 h-24 object-cover rounded-md'}
                                    src="https://res.cloudinary.com/dq6f7y2tu/image/upload/v1777454306/Hair_saloon_Photos_-_Download_Free_High-Quality_Pictures___Freepik_n0xfe9.jpg"
                                    alt=""/>
                                <IconButton className={''}
                                            color={'error'}
                                            size={'small'}
                                            sx={{
                                                position: "absolute",
                                                top: 0,
                                                right: 0
                                            }}>
                                    <Close sx={{fontSize: "1rem"}}/>
                                </IconButton>
                            </div> :
                            <>
                                <input type={'file'}
                                       accept={'image/*'}
                                       id={'fileInput'}
                                       style={{display: 'none'}}/>
                                <label
                                    className={'relative'}
                                    htmlFor={'fileInput'}>
                                <span
                                    className={'w-24 h-24 cursor-pointer flex items-center justify-center p-3 border rounded-md border-gray-400'}>
                                    <AddPhotoAlternate className={'text-gray-700'}/>
                                </span>
                                    {false && <div
                                        className={'absolute left-0 right-0 top-0 bottom-0 w-24 h-24 flex justify-center items-center'}>
                                        <CircularProgress/>
                                    </div>}
                                </label>
                            </>}
                    </Grid>
                    <Grid size={12}>
                        <TextField fullWidth
                                   id={"name"}
                                   name={'name'}
                                   label={'Name'}
                                   value={formik.values.name}
                                   onChange={formik.handleChange}
                                   required
                        />
                    </Grid>

                    <Grid size={12}>
                        <TextField fullWidth
                                   id={"description"}
                                   name={'description'}
                                   label={'Description'}
                                   value={formik.values.description}
                                   multiline
                                   rows={4}
                                   onChange={formik.handleChange}
                                   required
                        />
                    </Grid>

                    <Grid size={{xs: 12, sm: 6}}>
                        <TextField fullWidth
                                   id={"price"}
                                   name={'price'}
                                   label={'Price'}
                                   value={formik.values.price}
                                   onChange={formik.handleChange}
                                   required
                        />
                    </Grid>

                    <Grid size={{xs: 12, sm: 6}}>
                        <TextField fullWidth
                                   id={"duration"}
                                   name={'duration'}
                                   label={'Duration'}
                                   value={formik.values.duration}
                                   onChange={formik.handleChange}
                                   required
                        />
                    </Grid>

                    <Grid size={{xs: 12}}>
                        <FormControl fullWidth>
                            <InputLabel id="demo-simple-select-label">Category</InputLabel>
                            <Select
                                id="category"
                                value={formik.values.category}
                                label="Category"
                                name="category"
                                onChange={formik.handleChange}
                            >
                                {[1, 1, 1, 1].map((item, index) => <MenuItem
                                    value={"haircut" + index}>Haircut</MenuItem>)}
                            </Select>
                        </FormControl>

                    </Grid>

                    <Grid size={12}>
                        <Button type={'submit'}
                                variant={'outlined'}
                                fullWidth
                                sx={{py: '.8rem'}}>
                            Create Category
                        </Button>
                    </Grid>
                </Grid>
            </form>
        </div>
    );
};

export default CreateServiceForm;