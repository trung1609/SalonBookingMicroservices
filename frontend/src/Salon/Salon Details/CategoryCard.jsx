import React from 'react';

const CategoryCard = ({handleCategoryClick, selectedCategory, item}) => {
    return (
        <div
            onClick={handleCategoryClick}
            className={`px-3 py-2 cursor-pointer flex gap-2 items-center 
            ${selectedCategory === item
                ? "bg-green-500 text-white rounded-md"
                : ""}`}>
            <img className={'w-14 h-14 object-cover rounded-full'}
                 src="https://res.cloudinary.com/dq6f7y2tu/image/upload/v1777125265/download_fbidot.jpg" alt=""/>
            <h1>Hair Cut</h1>
        </div>
    );
};

export default CategoryCard;