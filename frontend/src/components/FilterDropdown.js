import { Popover, PopoverButton, PopoverPanel } from "@headlessui/react";
import { ChevronDownIcon } from "@heroicons/react/20/solid";
import { useState } from "react";

export default function FilterDropdown({id, name, options, onFilterChange, asArray}){
    const [filter, setFilter] = useState(asArray ? [] : undefined);

    const toggleFilter = (value) => {
        let newFilter;

        if(asArray) {
            if(!filter.includes(value)) {
                newFilter = [...filter, value];
            } else {
                newFilter = [...filter];
                const index = newFilter.indexOf(value);
                newFilter.splice(index, 1);
            }
        } else {
            if(filter === value) {
                newFilter = undefined
            } else {
                newFilter = value;
            }
        }
        setFilter(newFilter)
        onFilterChange?.(newFilter);
    }

    return (
        <Popover key={name} id="menu" className="relative inline-block text-left">
        <div>
            <PopoverButton className="group inline-flex items-center justify-center text-sm font-medium text-gray-700 hover:text-gray-900">
                <span>{name}</span>
                {asArray && filter.length > 0 ? (
                    <span className="ml-1.5 rounded bg-gray-200 px-1.5 py-0.5 text-xs font-semibold tabular-nums text-gray-700">
                        {filter.length}
                    </span>
                ) : null}
                <ChevronDownIcon
                    aria-hidden="true"
                    className="-mr-1 ml-1 h-5 w-5 flex-shrink-0 text-gray-400 group-hover:text-gray-500"
                />
            </PopoverButton>
        </div>

        <PopoverPanel
            transition
            className="absolute right-0 z-10 mt-2 origin-top-right rounded-md bg-white p-4 shadow-2xl ring-1 ring-black ring-opacity-5 transition focus:outline-none data-[closed]:scale-95 data-[closed]:transform data-[closed]:opacity-0 data-[enter]:duration-100 data-[leave]:duration-75 data-[enter]:ease-out data-[leave]:ease-in"
        >
            <form className="space-y-4">
                {options.map((option, optionIdx) => (
                    <div key={option.value} className="flex items-center" >
                        <input
                            checked={(asArray && filter.includes(option.value)) || (!asArray && filter === option.value)}
                            onChange={() => toggleFilter(option.value)}
                            id={`filter-${id}-${optionIdx}`}
                            name={`${id}[]`}
                            type="checkbox"
                            className="h-4 w-4 rounded border-gray-300 text-indigo-600 focus:ring-indigo-500"
                        />
                        <label
                            htmlFor={`filter-${id}-${optionIdx}`}
                            className="ml-3 whitespace-nowrap pr-6 text-sm font-medium text-gray-900"
                        >
                            {option.label}
                        </label>
                    </div>
                ))}
            </form>
        </PopoverPanel>
    </Popover>
    );
}