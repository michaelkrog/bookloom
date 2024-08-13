import { useState } from "react";
import filters from "../filters";
import { Menu, MenuButton, MenuItem, MenuItems, PopoverGroup } from "@headlessui/react";
import { ChevronDownIcon } from '@heroicons/react/20/solid'
import { CheckIcon } from "@heroicons/react/20/solid";
import FilterDropdown from "./FilterDropdown";

/**
 * BookFilter Component
 * 
 * This component provides a user interface for sorting and filtering a list of books.
 * It allows users to choose a sorting order (e.g., by price) and apply various filters
 * to the book list. The component uses the Headless UI library for dropdown menus and popovers.
 * 
 * Props:
 * - `onSortChange` (function): A callback function that is triggered when the sorting option changes.
 * - `onFilterChange` (function): A callback function that is triggered when any filter is applied or changed.
 * 
 * Internal State:
 * - `sort` (string): Holds the currently selected sorting option.
 * - `filter` (object): Holds the current filter criteria applied by the user.
 * 
 * Usage:
 * ```jsx
 * <BookFilter onSortChange={handleSortChange} onFilterChange={handleFilterChange} />
 * ```
 * 
 * @component
 * @example
 * const handleSortChange = (sortOption) => {
 *   // Update the book list based on the selected sort option
 * };
 * 
 * const handleFilterChange = (filters) => {
 *   // Update the book list based on the applied filters
 * };
 * 
 * <BookFilter onSortChange={handleSortChange} onFilterChange={handleFilterChange} />
 */
export default function BookFilter({onSortChange, onFilterChange}) {
    const [sort, setSort] = useState('price');
    const [filter, setFilter] = useState({});

    const sortOptions = [
        { name: 'Price: Low to High', value: 'price' },
        { name: 'Price: High to Low', value: 'price,desc' },
    ]

    const adjustFilter = (id, value) => {
        const newFilter = {...filter, filtered: true};

        const asArray = filters.find(f => f.id === id)?.asArray === true;

        if(asArray) {
            newFilter[id] = value.length > 0 ? value : undefined;
        } else {
            newFilter[id] = value;
        }
        setFilter(newFilter);
        onFilterChange?.(newFilter);
    }

    const adjustSort = (value) => {
        setSort(value);
        onSortChange?.(value);
    }


    return (

        <section aria-labelledby="filter-heading" className="border-t border-gray-200 pt-6">
            <h2 id="filter-heading" className="sr-only">
                Product filters
            </h2>

            <div className="flex items-center justify-between">
                <Menu as="div" className="relative inline-block text-left">
                    <div>
                        <MenuButton className="group inline-flex justify-center text-sm font-medium text-gray-700 hover:text-gray-900">
                            Sort
                            <ChevronDownIcon
                                aria-hidden="true"
                                className="-mr-1 ml-1 h-5 w-5 flex-shrink-0 text-gray-400 group-hover:text-gray-500"
                            />
                        </MenuButton>
                    </div>

                    <MenuItems
                        transition
                        className="absolute left-0 z-10 mt-2 w-48 origin-top-left rounded-md bg-white shadow-2xl ring-1 ring-black ring-opacity-5 transition focus:outline-none data-[closed]:scale-95 data-[closed]:transform data-[closed]:opacity-0 data-[enter]:duration-100 data-[leave]:duration-75 data-[enter]:ease-out data-[leave]:ease-in"
                    >
                        <div className="py-1">
                            {sortOptions.map((option) => (
                                <MenuItem key={option.value}>
                                    <a onClick={() => adjustSort(option.value)}
                                        className="group flex w-full items-center gap-2 px-4 py-2 text-sm font-medium text-gray-900 data-[focus]:bg-gray-100"
                                    >
                                        {sort === option.value ? <CheckIcon className="size-4 fill-black/30" /> : <div className="size-4" />}
                                        {option.name}
                                    </a>

                                </MenuItem>
                            ))}
                        </div>
                    </MenuItems>
                </Menu>
                {/* 
                        <button
                            type="button"
                            onClick={() => setMobileFiltersOpen(true)}
                            className="inline-block text-sm font-medium text-gray-700 hover:text-gray-900 sm:hidden"
                        >
                            Filters
                        </button>*/}

                <PopoverGroup className="hidden sm:flex sm:items-baseline sm:space-x-8">
                    {filters.map((section, sectionIdx) => (
                        <FilterDropdown key={section.id} name={section.name} options={section.options} asArray={section.asArray === true} onFilterChange={(filter) => adjustFilter(section.id, filter)}></FilterDropdown>
                        
                    ))}
                </PopoverGroup>
            </div>
        </section>
    )
}