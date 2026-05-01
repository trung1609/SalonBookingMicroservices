import React, {useEffect, useState} from 'react';
import CategoryCard from "./CategoryCard";
import ServiceCard from "./ServiceCard";
import {Button, Divider} from "@mui/material";
import {RemoveShoppingCart, ShoppingCart} from "@mui/icons-material";
import SelectedServiceList from "./SelectedServiceList";
import {useParams} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {fetchServicesBySalonId} from "../../../Redux/Salon Services/action";

const SalonServiceDetails = () => {
    const [selectedCategory, setSelectedCategory] = useState(null)
    const handleCategoryClick = (category) => () => {
        setSelectedCategory(category.id)
    }
    const {id} = useParams();
    const dispatch = useDispatch();
    const {salon, service, category} = useSelector(store => store);

    useEffect(() => {
        dispatch(fetchServicesBySalonId({
            salonId: id,
            jwt: localStorage.getItem("jwt"),
            categoryId: selectedCategory
        }));
    }, [id, selectedCategory]);
    return (
        <div className={'lg:flex gap-5 h-[90vh] mt-10'}>
            <section className={'space-y-5 border-r lg:w-[25%] pr-5'}>
                {category.categories.map((item, index) => <CategoryCard
                    selectedCategory={selectedCategory}
                    item={item}
                    handleCategoryClick={handleCategoryClick(item)}/>)}
            </section>

            <section className={'space-y-2 lg:w-[50%] px-5 lg:px-20 overflow-y-auto'}>

                {service.services.map((item) =>
                    <div className={'space-y-4'}>
                        <ServiceCard item={item}/>
                        <Divider/>
                    </div>)}
            </section>

            <section className={'lg:w-[25%]'}>
                <div className={'border rounded-md px-5'}>

                    {true ? <div>
                            <div className={'flex items-center gap-2'}>
                                <ShoppingCart sx={{fontSize: "30px", color: "green"}}/>
                                <h1 className={'font-thin text-sm'}>Selected Service</h1>
                            </div>
                            <SelectedServiceList/>
                            <Button sx={{py: ".7rem"}} fullWidth variant={"contained"}>Book Now</Button>
                        </div> :
                        <div className={'flex flex-col gap-3 items-center justify-center'}>
                            <RemoveShoppingCart sx={{fontSize: "30px", color: "green"}}/>
                            <h1>Not selected</h1>
                        </div>
                    }


                </div>
            </section>
        </div>
    );
};

export default SalonServiceDetails;